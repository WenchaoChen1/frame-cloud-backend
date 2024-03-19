// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.menu;


import com.gstdev.cloud.data.jpa.annotations.Query;
import com.gstdev.template.common.base.baseTree.BaseTreeFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuFindAllByQueryCriteria extends BaseTreeFindAllByQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;

  @Query(propName = "tenantId", joinName = "rTenantMenus", join = Query.Join.LEFT)
  String tenantId;
}

