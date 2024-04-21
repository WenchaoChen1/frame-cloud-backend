// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.tenant;

import com.gstdev.cloud.data.core.annotations.Query;
import com.frame.template.common.base.baseTree.BaseTreeFindAllByQueryCriteria;
import lombok.Data;

import java.util.List;


@Data
public class TenantFindAllByQueryCriteria extends BaseTreeFindAllByQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;
  @Query(blurry = "tenantName", type = Query.Type.IN)
  private String tenantName;
  @Query
  private String tenantId;
  @Query(blurry = "tenantId", type = Query.Type.IN)
  private List<String> tenantIds;


}

