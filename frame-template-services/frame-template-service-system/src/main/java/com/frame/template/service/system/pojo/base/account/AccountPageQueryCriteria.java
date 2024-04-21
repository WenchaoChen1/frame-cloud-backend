// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.account;

import com.gstdev.cloud.data.core.annotations.Query;
import com.frame.template.common.base.BasePageQueryCriteria;
import lombok.Data;

@Data
public class AccountPageQueryCriteria extends BasePageQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;
  @Query
  private String tenantId;
}

