// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.service;


import com.frame.template.service.demo.pojo.base.demoTree.*;
import com.gstdev.cloud.data.core.pojo.BaseTreeService;


public interface DemoTreeService extends BaseTreeService<DemoTreeDto,
        DemoTreeInsertInput, DemoTreeUpdateInput,
        DemoTreePageQueryCriteria, DemoTreeFindAllByQueryCriteria> {

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
}

