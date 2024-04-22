// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.service;


import com.gstdev.cloud.data.core.pojo.BaseTreeService;
import com.frame.template.service.demo.pojo.base.demoTree.*;


public interface DemoTreeService extends BaseTreeService<DemoTreeDto,
  DemoTreeInsertInput, DemoTreeUpdateInput,
  DemoTreePageQueryCriteria, DemoTreeFindAllByQueryCriteria> {

  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
}

