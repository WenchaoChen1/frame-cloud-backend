// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.service;


import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.BaseService;
import com.gstdev.template.service.system.pojo.base.rTenantMenu.*;

public interface RTenantMenuService extends BaseService<RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {
    Result<String> insertTenantMenu(RTenantMenuInsertInput rTenantMenuInsertInput);

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
