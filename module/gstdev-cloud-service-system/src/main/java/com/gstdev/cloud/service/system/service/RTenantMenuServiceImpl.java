// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;


import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.pojo.base.rTenantMenu.*;
import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.service.system.mapper.RTenantMenuMapper;
import com.gstdev.cloud.service.system.repository.AccountRepository;
import com.gstdev.cloud.service.system.repository.MenuRepository;
import com.gstdev.cloud.service.system.repository.RTenantMenuRepository;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
public class RTenantMenuServiceImpl extends BasePOJOServiceImpl<RTenantMenu, String, RTenantMenuRepository, RTenantMenuMapper, RTenantMenuDto> implements RTenantMenuService {

    @Resource
    private MenuRepository MenuRepository;
    @Resource
    private RTenantMenuRepository rTenantMenuRepository;

    public RTenantMenuServiceImpl(RTenantMenuRepository rTenantMenuRepository, RTenantMenuMapper rTenantMenuMapper) {
        super(rTenantMenuRepository, rTenantMenuMapper);
    }

    public RTenantMenuRepository getRepository() {
        return rTenantMenuRepository;
    }

    public List<RTenantMenu> findAllByTenantId(String tenantId) {
        return getRepository().findByTenantId(tenantId);
    }


    @Override
    @Transactional
    public Result<String> insertTenantMenu(RTenantMenuInsertInput rTenantMenuInsertInput) {
        List<RTenantMenu> allByTenantId = findAllByTenantId(rTenantMenuInsertInput.getTenantId());
        List<String> menuIds = rTenantMenuInsertInput.getMenuIds();
        List<RTenantMenu> rTenantMenus = new ArrayList<>();
        allByTenantId.forEach(rTenantMenu -> {
            if (menuIds.contains(rTenantMenu.getMenu().getId())) {
                menuIds.remove(rTenantMenu.getMenu().getId());
                rTenantMenus.add(rTenantMenu);
            } else {
                getRepository().delete(rTenantMenu);
            }
        });
        if (menuIds.size() > 0) {
            menuIds.forEach(id -> {
                RTenantMenu rTenantMenu = new RTenantMenu();
                rTenantMenu.setTenantId(rTenantMenuInsertInput.getTenantId());
                rTenantMenu.setMenu(MenuRepository.findById(id).get());
                rTenantMenus.add(rTenantMenu);
            });
        }
        updateAll(rTenantMenus);
        return Result.success();
    }

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////


}
