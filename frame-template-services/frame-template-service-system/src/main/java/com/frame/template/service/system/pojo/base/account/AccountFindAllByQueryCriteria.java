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
import com.frame.template.common.base.BaseFindAllByQueryCriteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFindAllByQueryCriteria extends BaseFindAllByQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;
  @Query
  private String tenantId;
}

