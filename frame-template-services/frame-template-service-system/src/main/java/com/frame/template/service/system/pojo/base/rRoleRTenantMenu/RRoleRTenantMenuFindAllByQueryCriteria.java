// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rRoleRTenantMenu;

import com.gstdev.cloud.data.jpa.annotations.Query;
import com.frame.template.common.base.BaseFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;
@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuFindAllByQueryCriteria extends BaseFindAllByQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;
//  @Query(type = Query.Type.IN)
// private Set<String> nailedType;
  @Query
  private String tenantId;
}

