// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.pojo.base.tenant.*;


public interface TenantService extends BaseTreeService<Tenant, String, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {
//
//  Result<List<TenantDto>> getTenantTree();
//
//  Result<TenantDto> updateCurrentLoginInferiorSonTenant(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
}

