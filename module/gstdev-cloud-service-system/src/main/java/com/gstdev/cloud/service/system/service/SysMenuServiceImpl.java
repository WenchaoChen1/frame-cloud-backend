package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.*;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.enums.SysUserType;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsDto;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.AccountMenuPermissionsQO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.InsertMenuManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.UpdateMenuManageIO;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.repository.SysAccountRepository;
import com.gstdev.cloud.service.system.repository.SysMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, String, SysMenuRepository> implements SysMenuService {

    @Resource
    private SysAccountRepository accountRepository;
    @Resource
    @Lazy
    private SysTenantMenuService tenantMenuService;
    @Resource
    @Lazy
    private SysMenuServiceImpl service;
    @Resource
    private SysMenuRepository menuRepository;
    @Resource
    private SysMenuMapper menuMapper;


    public SysMenuServiceImpl(SysMenuRepository menuRepository, SysMenuMapper menuMapper) {
        super(menuRepository);
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public SysMenuRepository getRepository() {
        return menuRepository;
    }

    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    @Override
    public SysMenuServiceImpl getService() {
        return service;
    }
    public List<SysMenu> findAllMenuByTenantId(String tenantId) {
        return tenantMenuService.findAllByTenantId(tenantId).stream().map(SysTenantMenu::getMenu).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends SysMenu> findAllMenuByAccount(SysAccount account) {
        return List.of();
    }

    @Override
    public Collection<? extends SysMenu> findAllMenuByUser(SysUser user) {
        return List.of();
    }

    @Override
    public List<String> getPermissionsByMenuIds(Set<String> menuIds) {
        List<SysMenu> menus = menuRepository.findAllById(menuIds);
        return getService().getPermissionsByMenus(menus);
    }

    @Override
    public List<String> getPermissionsByMenus(List<SysMenu> menus) {
        Map<String, Set<String>> attributeMaps = new HashMap<>();
        // 处理菜单根据menuId去重
        menus.stream().collect(Collectors.toMap(SysMenu::getId, menu -> menu, (key1, key2) -> key1));
        // 处理菜单的属性并进行分组
        menus.forEach(sysMenu -> {
            sysMenu.getAttributes().stream()
                    .collect(Collectors.groupingBy(SysAttribute::getServiceId))
                    .forEach((serviceId, attributes) -> {
                        Set<String> sysAttributes = attributeMaps.getOrDefault(serviceId, new HashSet<>());
                        sysAttributes.addAll(attributes.stream().map(SysAttribute::getAttributeId).collect(Collectors.toSet()));
                        attributeMaps.put(serviceId, sysAttributes);
                    });
        });
        List<String> collect = new ArrayList<>();
        // 将分组后的属性ID集合生成权限并添加到权限集合中
        for (Set<String> value : attributeMaps.values()) {
            collect.add(generateKey(new ArrayList<>(value)));
        }
        return collect;
    }

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
    @Override
    public List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        AccountMenuPermissionsQO accountMenuPermissionsQO = new AccountMenuPermissionsQO();
        List<SysMenu> sysMenuList = new ArrayList<>();
        SysUser user = account.getUser();
        if (user.getType().equals(SysUserType.SUPER)) {
            sysMenuList = service.findAll();
        }
        if (user.getType().equals(SysUserType.USER)) {
            if (account.getType().equals(SysAccountType.ADMIN)) {
                sysMenuList = findAllMenuByTenantId(account.getTenantId());
            } else if (account.getType().equals(SysAccountType.USER)) {
                Map<String, SysMenu> collect = new HashMap<>();
                for (SysRole role : account.getRoles()) {
                    Map<String, SysMenu> collect1 = role.getTenantMenus().stream().map(SysTenantMenu::getMenu).collect(Collectors.groupingBy(SysMenu::getId,
                            Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
                    collect.putAll(collect1);
                }
                sysMenuList = collect.values().stream().toList();
            }
        }
        sysMenuList = sysMenuList.stream().filter(sysMenu -> sysMenu.getStatus().equals(DataItemStatus.ENABLE)).toList();
        return getMapper().toAccountMenuPermissionsDtoToTree(sysMenuList);
    }

    @Override
    @Transactional
    public void insertMenuManage(InsertMenuManageIO insertMenuManageIO) {
        SysMenu entity = getMapper().toEntity(insertMenuManageIO);
        getService().saveAndFlush(entity);
    }



    @Override
    @Transactional
    public void updateMenuManage(UpdateMenuManageIO updateMenuManageIO) {
        SysMenu sysMenu = service.findById(updateMenuManageIO.getId());
        menuMapper.copy(updateMenuManageIO, sysMenu);
        service.saveAndFlush(sysMenu);
    }
    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
