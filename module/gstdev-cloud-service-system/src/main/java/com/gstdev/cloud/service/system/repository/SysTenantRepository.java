// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.repository;


import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;


import java.util.List;

public interface SysTenantRepository extends BaseTreeRepository<Tenant, String> {
    List<Tenant> findAllByParentId(String parentId);

}

