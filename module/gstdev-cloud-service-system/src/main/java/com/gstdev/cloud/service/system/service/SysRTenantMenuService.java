// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.pojo.base.rTenantMenu.*;

public interface SysRTenantMenuService extends BasePOJOService<RTenantMenu, String, RTenantMenuDto> {
    Result<String> insertTenantMenu(RTenantMenuInsertInput rTenantMenuInsertInput);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
