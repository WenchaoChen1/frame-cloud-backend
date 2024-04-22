// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.service;


import com.frame.template.service.system.pojo.base.rTenantMenu.*;
import com.frame.template.service.system.pojo.domain.RTenantMenu;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOService;

public interface RTenantMenuService extends BasePOJOService<RTenantMenu, String,RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {
  Result<String> insertTenantMenu(RTenantMenuInsertInput rTenantMenuInsertInput);

  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}
