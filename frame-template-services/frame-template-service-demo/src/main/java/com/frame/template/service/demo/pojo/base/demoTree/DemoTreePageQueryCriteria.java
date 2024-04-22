// ====================================================
//
// This file is part of the CSCEC81 Cloud Platform.
//
// Create by CSCEC81 Technology <support@cscec81.com>
// Copyright (c) 2020-2021 cscec81.com
//
// ====================================================

package com.frame.template.service.demo.pojo.base.demoTree;

import com.gstdev.cloud.data.core.pojo.BaseTreePageQueryCriteria;
import lombok.Data;


@Data
public class DemoTreePageQueryCriteria extends BaseTreePageQueryCriteria {

  private static final long serialVersionUID = 3163118978801722144L;
  //  @Query(type = Query.Type.IN)
// private Set<String> nailedType;
  private String parentId;
}

