// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.repository;


import com.frame.template.common.base.baseTree.BaseTreeRepository;
import com.frame.template.service.system.pojo.domain.Tenant;


import java.util.List;

public interface TenantRepository extends BaseTreeRepository<Tenant> {
  List<Tenant> findAllByParentId(String parentId);

}

