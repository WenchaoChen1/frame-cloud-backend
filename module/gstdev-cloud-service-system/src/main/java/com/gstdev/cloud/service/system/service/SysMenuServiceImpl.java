package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.service.system.domain.base.menu.MenuDto;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, String, SysMenuRepository> implements SysMenuService {

    @Resource
    private SysAccountRepository accountRepository;
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

    @Override
    public List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        AccountMenuPermissionsQO accountMenuPermissionsQO = new AccountMenuPermissionsQO();
        List<SysMenu> sysMenuList = new ArrayList<>();
        if (account.getType().equals(SysAccountType.SUPER)) {
            sysMenuList = service.findAll();
        } else if (account.getType().equals(SysAccountType.ADMIN)) {
            accountMenuPermissionsQO.setTenantId(account.getTenantId());
            sysMenuList = service.findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, accountMenuPermissionsQO, criteriaBuilder));
        } else if (account.getType().equals(SysAccountType.USER)) {
            Map<String, SysMenu> collect = new HashMap<>();
            for (SysRole role : account.getRoles()) {
                Map<String, SysMenu> collect1 = role.getTenantMenus().stream().map(SysTenantMenu::getMenu).collect(Collectors.groupingBy(SysMenu::getId,
                    Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
                collect.putAll(collect1);
            }
            sysMenuList = collect.values().stream().toList();
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
