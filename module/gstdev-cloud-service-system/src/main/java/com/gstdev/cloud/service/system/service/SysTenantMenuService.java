// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;

public interface SysTenantMenuService extends BasePOJOService<SysTenantMenu, String, RTenantMenuDto> {
   void insertTenantMenu(InsertTenantMenuIO insertTenantMenuIO);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
