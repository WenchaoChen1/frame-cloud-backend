// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.demo.controller;

import com.gstdev.template.common.base.baseTree.BaseTreeController;
import com.gstdev.template.service.demo.pojo.base.demoTree.*;
import com.gstdev.template.service.demo.mapper.vo.DemoTreeVoMapper;
import com.gstdev.template.service.demo.service.DemoTreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/v1/demoTree")
public class DemoTreeController extends BaseTreeController<DemoTreeService, DemoTreeVoMapper, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreeVo, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreeDto, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreeInsertInput, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreeUpdateInput, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreePageQueryCriteria, com.gstdev.template.service.demo.pojo.base.demoTree.DemoTreeFindAllByQueryCriteria> {

  @Resource
  private DemoTreeService demoTreeService;

  @Resource
  private DemoTreeVoMapper demoTreeVoMapper;

  public DemoTreeController(DemoTreeService demoTreeService, DemoTreeVoMapper demoTreeVoMapper) {
    super(demoTreeService, demoTreeVoMapper);
    this.demoTreeService = demoTreeService;
    this.demoTreeVoMapper = demoTreeVoMapper;
  }

}

