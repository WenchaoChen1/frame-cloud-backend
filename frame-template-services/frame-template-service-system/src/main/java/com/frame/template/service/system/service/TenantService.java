// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.tenant.*;
import com.frame.template.service.system.pojo.domain.Tenant;
import com.gstdev.cloud.data.core.service.BaseTreeService;


public interface TenantService extends BaseTreeService<Tenant, String, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {
//
//  Result<List<TenantDto>> getTenantTree();
//
//  Result<TenantDto> updateCurrentLoginInferiorSonTenant(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
}

