// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.tenant;

import com.gstdev.cloud.data.jpa.annotations.Query;
import com.frame.template.common.base.baseTree.BaseTreePageQueryCriteria;
import lombok.Data;


@Data
public class TenantPageQueryCriteria extends BaseTreePageQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;

  @Query(blurry = "tenantName", type = Query.Type.IN)
  private String tenantName;

}

