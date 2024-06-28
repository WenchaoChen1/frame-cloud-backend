// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.TenantMenuMenuTreeDto;

import java.util.List;

public interface SysTenantMenuService extends BaseService<SysTenantMenu, String> {
    void insertTenantMenu(InsertTenantMenuIO insertTenantMenuIO);

    List<SysTenantMenu> findAllByTenantId(String tenantId);

    /**
     *
     * @param tenantId
     * @return
     */
    List<TenantMenuMenuTreeDto> getAllTenantMenuMenuTree(String tenantId);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
