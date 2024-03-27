// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.controller;

import com.frame.template.service.demo.mapper.vo.DemoTemplateVoMapper;
import com.frame.template.service.demo.pojo.vo.demoTemplate.*;
import com.frame.template.service.demo.service.DemoTemplateService;
import com.gstdev.cloud.commons.domain.Result;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/demoTemplate")
public class DemoTemplateController {

  @Resource
  private DemoTemplateService demoTemplateService;

  @Resource
  private DemoTemplateVoMapper demoTemplateVoMapper;

  @PostMapping
  @ApiOperation("insert")
  public Result<DemoTemplateVo> insert(@RequestBody DemoTemplateInsertInput demoTemplateInsertInput) {
    return demoTemplateVoMapper.toVo(demoTemplateService.insert(demoTemplateInsertInput));
  }

  @PutMapping
  @ApiOperation("update")
  public Result<DemoTemplateVo> update(@RequestBody DemoTemplateUpdateInput demoTemplateUpdateInput) {
    return demoTemplateVoMapper.toVo(demoTemplateService.update(demoTemplateUpdateInput));
  }


  @ApiOperation("删除")
  @DeleteMapping
  public Result<DemoTemplateVo> deleteById(String id) {
    return demoTemplateVoMapper.toVo(demoTemplateService.deleteById(id));
  }

  @GetMapping("/page")
  @ApiOperation("page")
  public Page<DemoTemplateVo> page(DemoTemplatePageQueryCriteria queryCriteria, Pageable pageable) {
    return demoTemplateVoMapper.toVo(demoTemplateService.page(queryCriteria, pageable));
  }


  @GetMapping
  @ApiOperation("find-by-id")
  public DemoTemplateVo findById(String id) {
    return demoTemplateVoMapper.toVo(demoTemplateService.findById(id));
  }

  @GetMapping("/find-all-by-query-criteria")
  public List<DemoTemplateVo> findAllByQueryCriteria(DemoTemplateFindAllByQueryCriteria queryCriteria) {
    return demoTemplateVoMapper.toVo(demoTemplateService.findAllByQueryCriteria(queryCriteria));
  }


  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////


}

