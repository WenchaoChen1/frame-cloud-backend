// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.controller;

import com.frame.template.common.base.baseTree.BaseTreeController;
import com.frame.template.service.demo.mapper.vo.DemoTreeVoMapper;
import com.frame.template.service.demo.pojo.base.demoTree.*;
import com.frame.template.service.demo.service.DemoTreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/v1/demoTree")
public class DemoTreeController extends BaseTreeController<DemoTreeService, DemoTreeVoMapper, DemoTreeVo, DemoTreeDto, DemoTreeInsertInput, DemoTreeUpdateInput, DemoTreePageQueryCriteria, DemoTreeFindAllByQueryCriteria> {

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

