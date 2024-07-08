package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.service.system.domain.entity.*;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.enums.SysTenantPermissionType;
import com.gstdev.cloud.service.system.domain.enums.SysUserType;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysSecurityServiceImpl implements SysSecurityService {


    @Resource
    @Lazy
    private SysTenantService sysTenantService;

    @Resource
    private SysTenantMenuService sysTenantMenuService;
    @Resource
    private SysRAccountTenantMenuService sysRAccountTenantMenuService;
    @Resource
    private SysRAccountBusinessPermissionService sysRAccountBusinessPermissionService;
    @Resource
    private SysRAccountRoleService sysRAccountRoleService;
    @Resource
    private SysRRoleTenantMenuService sysRRoleTenantMenuService;
    @Resource
    private SysRRoleBusinessPermissionService sysRRoleBusinessPermissionService;
    @Resource
    private SysBusinessPermissionService sysBusinessPermissionService;
    @Resource
    private SysRTenantMenuBusinessPermissionService sysRTenantMenuBusinessPermissionService;

    /**
     * 获取用户的权限
     * @param user
     * @return
     */
    @Override
    public Set<FrameGrantedAuthority> getUserAuthoritiesPermissions(SysUser user) {
        // 初始化权限集合
        Set<FrameGrantedAuthority> authorities = new HashSet<>();

        // 添加超级管理员的特殊权限
        if (user.getType().equals(SysUserType.SUPER)) {
            authorities.add(new FrameGrantedAuthority("5ef5ef0364b6939c4ca61f34b393f7b368d1be8619647aaf83d5b395919ab629"));
            return authorities;
        }
        if (user.getType().equals(SysUserType.USER)) {
            getAccountAuthoritiesPermissions(user.getAccount());
        }
        return authorities;
    }

    /**
     * 获取账户的权限
     * @param sysAccounts
     * @return
     */
    public Set<FrameGrantedAuthority> getAccountAuthoritiesPermissions(List<SysAccount> sysAccounts) {
        Set<FrameGrantedAuthority> authorities = new HashSet<>();
        // 获取用户的账号列表
        sysAccounts = sysAccounts.stream()
                .filter(account -> account.getStatus().equals(DataItemStatus.ENABLE)).toList();
        List<SysMenu> accountSysMenu = getAccountSysMenu(sysAccounts);

        List<String> permissionsByTenantMenuIds = getPermissionsByMenus(accountSysMenu);
        for (String permissionsByMenuId : permissionsByTenantMenuIds) {
            authorities.add(new FrameGrantedAuthority(permissionsByMenuId));
        }
        return authorities;
    }

    /**
     * 获取账户的菜单
     *
     * @param accounts
     * @return
     */
    public List<SysMenu> getAccountSysMenu(List<SysAccount> accounts) {
        Set<String> tenantIds = accounts.stream().map(SysAccount::getTenantId).collect(Collectors.toSet());
        Map<String, SysTenant> tenantMap = sysTenantService.findAllByIds(tenantIds).stream()
                .collect(Collectors.toMap(
                        SysTenant::getId,
                        employee -> employee,
                        (existing, replacement) -> existing
                ));

        // 初始化租户菜单相关集合
        Set<String> userTenantMenuIds = new HashSet<>();
        Set<String> adminAccountTenantIds = new HashSet<>();
        Set<String> accountTenantMenuAccountIds = new HashSet<>();
        Set<String> accountTenantBusinessPermissionAccountIds = new HashSet<>();
        Set<String> accountRoleAccountIds = new HashSet<>();
        Set<String> accountRoleMenuAccountIds = new HashSet<>();
        Set<String> accountRoleBusinessPermissionAccountIds = new HashSet<>();

        // 处理用户的租户账号
        accounts.forEach(account -> {
            SysTenant sysTenant = tenantMap.get(account.getTenantId());
            if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TYPE)) {
                if (account.getType().equals(SysAccountType.ADMIN)) {
                    adminAccountTenantIds.add(account.getTenantId());
                }
                if (account.getType().equals(SysAccountType.USER)) {
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TENANT_MENU)) {
                        accountTenantMenuAccountIds.add(account.getAccountId());
                    }
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_TENANT_BUSINESS_PERMISSION)) {
                        accountTenantBusinessPermissionAccountIds.add(account.getAccountId());
                    }

                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_ROLE_MENU)) {
                        accountRoleAccountIds.add(account.getAccountId());
                        accountRoleMenuAccountIds.add(account.getAccountId());
                    }
                    if (sysTenant.getTenantPermissionTypes().contains(SysTenantPermissionType.ACCOUNT_ROLE_BUSINESS_PERMISSION)) {
                        accountRoleAccountIds.add(account.getAccountId());
                        accountRoleBusinessPermissionAccountIds.add(account.getAccountId());
                    }
                }
            }
        });
        Set<String> tenantBusinessPermissionIds = new HashSet<>();

        if (!adminAccountTenantIds.isEmpty()) {
            userTenantMenuIds.addAll(sysTenantMenuService.getAllTenantMenuIdByTenantIdIn(adminAccountTenantIds));
        }

        if (!ObjectUtils.isEmpty(accountTenantMenuAccountIds)) {
            userTenantMenuIds.addAll(sysRAccountTenantMenuService.getAllTenantMenuIdByAccountIds(accountTenantMenuAccountIds));
        }
        if (!ObjectUtils.isEmpty(accountTenantBusinessPermissionAccountIds)) {
            tenantBusinessPermissionIds.addAll(sysRAccountBusinessPermissionService.getAllBusinessPermissionIdByAccountIds(accountTenantBusinessPermissionAccountIds));
        }
        if (!ObjectUtils.isEmpty(accountTenantBusinessPermissionAccountIds)) {
            List<String> allRoleIdByAccountIds = sysRAccountRoleService.getAllRoleIdByAccountIds(accountRoleAccountIds);
            if (!ObjectUtils.isEmpty(allRoleIdByAccountIds)) {
                if (!ObjectUtils.isEmpty(accountRoleMenuAccountIds)) {
                    userTenantMenuIds.addAll(sysRRoleTenantMenuService.getAllTenantMenuIdByRoleIds(allRoleIdByAccountIds));

                }
                if (!ObjectUtils.isEmpty(accountRoleBusinessPermissionAccountIds)) {
                    tenantBusinessPermissionIds.addAll(sysRRoleBusinessPermissionService.getAllBusinessPermissionIdByRoleIds(allRoleIdByAccountIds));

                }
            }
        }
        if (!ObjectUtils.isEmpty(tenantBusinessPermissionIds)) {
            userTenantMenuIds.addAll(sysRTenantMenuBusinessPermissionService.getAllTenantMenuIdByBusinessPermissionIds(tenantBusinessPermissionIds));
        }

        List<SysTenantMenu> sysTenantMenus = sysTenantMenuService.findAllById(userTenantMenuIds);
        return sysTenantMenus.stream().map(SysTenantMenu::getMenu).toList();
    }

    /**
     * 获取菜单的权限
     * @param menus
     * @return
     */
    public List<String> getPermissionsByMenus(List<SysMenu> menus) {
        Map<String, Set<String>> attributeMaps = new HashMap<>();
        // 处理菜单根据menuId去重
        menus.stream().collect(Collectors.toMap(SysMenu::getId, menu -> menu, (key1, key2) -> key1));
        // 处理菜单的属性并进行分组
        menus.forEach(sysMenu ->
                sysMenu.getAttributes().stream()
                        .collect(Collectors.groupingBy(SysAttribute::getServiceId))
                        .forEach((serviceId, attributes) -> {
                            Set<String> sysAttributes = attributeMaps.getOrDefault(serviceId, new HashSet<>());
                            sysAttributes.addAll(attributes.stream().map(SysAttribute::getAttributeId).collect(Collectors.toSet()));
                            attributeMaps.put(serviceId, sysAttributes);
                        })
        );
        List<String> collect = new ArrayList<>();
        // 将分组后的属性ID集合生成权限并添加到权限集合中
        for (Set<String> value : attributeMaps.values()) {
            collect.add(generateKey(new ArrayList<>(value)));
        }
        return collect;
    }

    /**
     * 生成权限key
     * @param input
     * @return
     */
    public static String generateKey(List<String> input) {
        // 对字符串列表进行排序
        Collections.sort(input);
        // 连接排序后的字符串
        String combinedInput = String.join("", input);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combinedInput.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
