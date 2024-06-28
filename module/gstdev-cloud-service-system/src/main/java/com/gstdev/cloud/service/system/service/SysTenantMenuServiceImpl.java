// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;


import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;
import com.gstdev.cloud.service.system.mapper.RTenantMenuMapper;
import com.gstdev.cloud.service.system.repository.SysMenuRepository;
import com.gstdev.cloud.service.system.repository.SysTenantMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
public class SysTenantMenuServiceImpl extends BaseServiceImpl<SysTenantMenu, String, SysTenantMenuRepository> implements SysTenantMenuService {

    @Resource
    private SysMenuRepository MenuRepository;
    @Resource
    private SysTenantMenuRepository rTenantMenuRepository;
    @Resource
    @Lazy
    private SysRoleService sysRoleService;
    @Resource
    @Lazy
    private RTenantMenuMapper rTenantMenuMappere;
    @Resource
    @Lazy
    private SysTenantMenuServiceImpl service;


    public SysTenantMenuServiceImpl(SysTenantMenuRepository rTenantMenuRepository, RTenantMenuMapper rTenantMenuMappere) {
        super(rTenantMenuRepository);
        this.rTenantMenuMappere = rTenantMenuMappere;
    }

    @Override
    public SysTenantMenuRepository getRepository() {
        return rTenantMenuRepository;
    }
    @Override
    public SysTenantMenuServiceImpl getService() {
        return service;
    }

    @Override
    public List<SysTenantMenu> findAllByTenantId(String tenantId) {
        return getRepository().findByTenantId(tenantId);
    }

    @Override
    public List<TenantMenuMenuTreeDto> getAllTenantMenuMenuTree(String tenantId) {
        List<SysTenantMenu> allByTenantId = getRepository().findByTenantId(tenantId);
        List<TenantMenuMenuTreeDto> tenantMenuMenuTreeDto = rTenantMenuMappere.toTenantMenuMenuTreeDto(allByTenantId);
        return tenantMenuMenuTreeDto;
    }

    @Override
    @Transactional
    public void insertTenantMenu(InsertTenantMenuIO insertTenantMenuIO) {
        List<SysTenantMenu> currentTenantMenus = findAllByTenantId(insertTenantMenuIO.getTenantId());
        List<String> menuIds = new ArrayList<>(insertTenantMenuIO.getMenuIds());
        List<SysRole> tenantRoles = sysRoleService.getAllByTenantId(insertTenantMenuIO.getTenantId());
        List<SysTenantMenu> newTenantMenus = new ArrayList<>();

        // 处理需要保留的菜单项和需要删除的菜单项
        List<SysTenantMenu> menusToDelete = new ArrayList<>();
        for (SysTenantMenu tenantMenu : currentTenantMenus) {
            if (menuIds.contains(tenantMenu.getMenu().getId())) {
                menuIds.remove(tenantMenu.getMenu().getId());
                newTenantMenus.add(tenantMenu);
            } else {
                menusToDelete.add(tenantMenu);
            }
        }

        // 批量更新角色的菜单项关联关系
        for (SysRole role : tenantRoles) {
            role.getTenantMenus().removeAll(menusToDelete);
            sysRoleService.update(role);
        }

        // 删除不再关联的菜单项
        for (SysTenantMenu menuToDelete : menusToDelete) {
            getRepository().delete(menuToDelete);
        }

        // 添加新的菜单项关联关系
        for (String menuId : menuIds) {
            SysTenantMenu newTenantMenu = new SysTenantMenu();
            newTenantMenu.setTenantId(insertTenantMenuIO.getTenantId());
            newTenantMenu.setMenu(MenuRepository.findById(menuId).orElse(null));
            newTenantMenus.add(newTenantMenu);
        }

        // 批量更新新的菜单项关联关系
        update(newTenantMenus);
    }
    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////


}
