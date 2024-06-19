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
import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuInsertInput;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;

import java.util.List;

public interface SysRTenantMenuService extends BasePOJOService<RTenantMenu, String, RTenantMenuDto> {
    Result<String> insertTenantMenu(String tenantId, List<String> menuIds);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
