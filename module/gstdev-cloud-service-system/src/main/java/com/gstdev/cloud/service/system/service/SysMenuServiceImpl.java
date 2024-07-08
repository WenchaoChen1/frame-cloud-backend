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
