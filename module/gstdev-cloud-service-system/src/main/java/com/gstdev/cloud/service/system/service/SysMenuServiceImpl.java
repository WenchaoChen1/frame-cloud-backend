package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
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
import com.gstdev.cloud.service.system.repository.SysRAttributeMenuRepository;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseTreeServiceImpl<SysMenu, String, SysMenuRepository, SysMenuMapper, MenuDto> implements SysMenuService {

    @Resource
    private SysAccountRepository accountRepository;
    @Resource
    @Lazy
    @Getter
    private SysMenuService service;
    @Resource
    private SysMenuRepository menuRepository;
    @Resource
    private SysRAttributeMenuRepository sysRAttributeMenuRepository;
    @Resource
    private SysMenuMapper menuMapper;


    public SysMenuServiceImpl(SysMenuRepository menuRepository, SysMenuMapper menuMapper) {
        super(menuRepository, menuMapper);
        this.menuRepository = menuRepository;
    }

    @Override
    public SysMenuRepository getRepository() {
        return menuRepository;
    }

    @Override
    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    @Override
    public List<AccountMenuPermissionsDto> getAccountMenuPermissions(String accountId) {
        SysAccount account = accountRepository.findById(accountId).get();
        AccountMenuPermissionsQO accountMenuPermissionsQO = new AccountMenuPermissionsQO();
        List<SysMenu> sysMenuList = new ArrayList<>();
        if (account.getType().equals(SysAccountType.SUPER)) {
            sysMenuList = getService().findAll();
        } else if (account.getType().equals(SysAccountType.ADMIN)) {
            accountMenuPermissionsQO.setTenantId(account.getTenantId());
            sysMenuList = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, accountMenuPermissionsQO, criteriaBuilder));
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
    public void insertAMenuManage(InsertMenuManageIO insertMenuManageIO) {
        SysMenu entity = getMapper().toEntity(insertMenuManageIO);
        SysMenu sysMenu = getService().saveAndFlush(entity);
        sysRAttributeMenuRepository.saveAndFlush(sysMenu.getId(), insertMenuManageIO.getAttributeIds());
    }

    @Override
    @Transactional
    public void updateMenuManage(UpdateMenuManageIO updateMenuManageIO) {
        SysMenu sysMenu = getService().findById(updateMenuManageIO.getId());
        menuMapper.copy(updateMenuManageIO, sysMenu);
        SysMenu sysMenu1 = getService().saveAndFlush(sysMenu);
        sysRAttributeMenuRepository.saveAndDeleteAndFlush(sysMenu1.getId(), updateMenuManageIO.getAttributeIds());
    }


    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}
