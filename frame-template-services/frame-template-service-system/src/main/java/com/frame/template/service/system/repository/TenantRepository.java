// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.repository;


import com.frame.template.service.system.pojo.domain.Tenant;
import com.gstdev.cloud.data.core.repository.BaseTreeRepository;


import java.util.List;

public interface TenantRepository extends BaseTreeRepository<Tenant, String> {
    List<Tenant> findAllByParentId(String parentId);

}

