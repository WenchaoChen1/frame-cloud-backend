package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.converter.SysUserToSecurityUserConverter;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import com.gstdev.cloud.service.system.domain.enums.SysTenantPermissionType;
import com.gstdev.cloud.service.system.domain.enums.SysUserType;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.feign.service.IdentityFeignService;
import com.gstdev.cloud.service.system.feign.vo.IdentitySaveDto;
import com.gstdev.cloud.service.system.mapper.SysUserMapper;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String, SysUserRepository> implements SysUserService {

    private static final String SPECIAL_CHARS = "! @#$%^&＊_=+-/";
    @Resource
    @Lazy
    private SysAccountService accountService;
    @Resource
    @Lazy
    private SysMenuService menuService;

    @Resource
    @Lazy
    private IdentityFeignService identityFeignService;
    @Resource
    private SysUserRepository userRepository;
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
    @Resource
    private SysTenantService sysTenantService;
    //    @Resource
    private SysUserMapper userMapper;

    public SysUserServiceImpl(SysUserRepository userRepository, SysUserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
    }

    private static char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return (char) ('a' + rnd.nextInt(26));
            case 1:
                return (char) ('A' + rnd.nextInt(26));
            case 2:
                return (char) ('0' + rnd.nextInt(10));
            default:
                return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
        }
    }

    public static String randomPassword() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        return new String(chars);
    }

    @Override
    public SysUserRepository getRepository() {
        return userRepository;
    }

    @Override
    @Transactional
    public SysUser save(SysUser user) {
        if (ObjectUtils.isEmpty(user.getPassword())) {
            user.setPassword(SecurityUtils.encrypt(randomPassword()));
        }
        return super.save(user);
    }

    @Override
    @Transactional
    public SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput) {
        SysUser user = userMapper.toEntity(userInsertInput);
        SysUser insert = insert(user);
        InsertAccountManageInitializationIO accountInsertInput = new InsertAccountManageInitializationIO();
        accountInsertInput.setTenantId(userInsertInput.getTenantId());
        accountInsertInput.setUserId(insert.getUserId());
//        accountInsertInput.setType(userInsertInput.getType());
        accountService.insertAccountManageInitialization(accountInsertInput);
        // 同步到identity模块
        IdentitySaveDto identitySaveDto = new IdentitySaveDto();
        identitySaveDto.setUserId(insert.getUserId());
        identitySaveDto.setEmail(insert.getEmail());
        identitySaveDto.setUsername(insert.getUsername());
        identitySaveDto.setPassword(insert.getPassword());
        identityFeignService.save(identitySaveDto);
        return insert;
    }



    /**
     * 新增用户并且创建账户角色，关联部门
     *
     * @param userInsertInput
     * @return
     */
    @Transactional
    public UserDto insertUserManageInitializationToDto(InsertUserManageInitializationIO userInsertInput) {
        SysUser sysUser = insertUserManageInitialization(userInsertInput);
        return userMapper.toDto(sysUser);
    }

    @Override
    public DefaultSecurityUser signInFindByUsername(String username) {
        // 根据用户名查找用户
        SysUser user = getRepository().findByUsername(username);
        if (user == null) {
            return null;
        }

        // 初始化权限集合
        Set<FrameGrantedAuthority> authorities = new HashSet<>();

        // 获取用户的账号列表
        List<SysAccount> accounts = user.getAccount().stream()
                .filter(account -> account.getStatus().equals(DataItemStatus.ENABLE)).toList();
        // 添加超级管理员的特殊权限
        if (user.getType().equals(SysUserType.SUPER)) {
            authorities.add(new FrameGrantedAuthority("5ef5ef0364b6939c4ca61f34b393f7b368d1be8619647aaf83d5b395919ab629"));
        }
        if (user.getType().equals(SysUserType.USER)) {
            Set<String> tenantIds = accounts.stream().map(SysAccount::getTenantId).collect(Collectors.toSet());
            Map<String, SysTenant> tenantMap = sysTenantService.findAllByIds(tenantIds).stream()
                    .collect(Collectors.toMap(
                            SysTenant::getId,
                            employee -> employee,
                            (existing, replacement) -> existing
                    ));
            if (ObjectUtils.isEmpty(authorities)) {
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

                List<String> permissionsByTenantMenuIds = sysTenantMenuService.getPermissionsByTenantMenuIds(userTenantMenuIds);
                for (String permissionsByMenuId : permissionsByTenantMenuIds) {
                    authorities.add(new FrameGrantedAuthority(permissionsByMenuId));
                }
            }

        }

        // 将SysUser对象转换为DefaultSecurityUser对象
        SysUserToSecurityUserConverter sysUserToSecurityUserConverter = new SysUserToSecurityUserConverter();
        return sysUserToSecurityUserConverter.convert(user,authorities);
    }


    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    public List<AccountListDto> getByIdToAccount(String id) {
        SysUser user = getRepository().findById(id).orElseGet(SysUser::new);
        return userMapper.accountListToDto(user.getAccount());
    }

    @Override
    @Transactional()
    public void deleteById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new PlatformRuntimeException("The primary key cannot be empty");
        }
        if (id.equals(SecurityUtils.getUserId())) {
            throw new PlatformRuntimeException("You cannot delete your own data");
        }
        getRepository().deleteById(id);
    }
    @Override
    @Transactional
    public void changeStatus(String userId, DataItemStatus status) {
        SysUser sysUser = getService().findById(userId);
        if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(sysUser)) {
            sysUser.setStatus(status);
            log.debug("[GstDev Cloud] |- Change user [{}] status to [{}]", sysUser.getUsername(), status.name());
            getService().save(sysUser);
        }

    }
    @Override
    @Transactional
    public void resetPassword(String originalPassword, String newPassword) {
        SysUser user = getRepository().findById(SecurityUtils.getUserId()).orElseGet(SysUser::new);
        if (!SecurityUtils.matches(originalPassword, user.getPassword())) {
            throw new PlatformRuntimeException("The original password is incorrect");
        }
        user.setPassword(SecurityUtils.encrypt(newPassword));
        save(user);
    }
    @Override
    @Transactional
    public void userManageResetPaaword(String newPassword, String userId) {
        SysUser sysUser = getService().findById(userId);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new PlatformRuntimeException("Reset failure");
        }
        sysUser.setPassword(SecurityUtils.encrypt(newPassword));
        log.debug("[GstDev Cloud] |- Reset user [{}] password to [{}]", sysUser.getUsername(), newPassword);
        getService().save(sysUser);
    }
}
