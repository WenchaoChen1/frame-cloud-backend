// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.DepartVoMapper;
import com.frame.template.service.system.pojo.base.depart.*;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeController;
import com.frame.template.service.system.pojo.base.depart.*;
import com.frame.template.service.system.service.DepartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/v1/depart")
public class DepartController extends BaseTreeController<DepartService, DepartVoMapper, DepartVo, DepartDto, DepartInsertInput, DepartUpdateInput, DepartPageQueryCriteria, DepartFindAllByQueryCriteria> {

  @Resource
  private DepartService departService;

  @Resource
  private DepartVoMapper departVoMapper;

  public DepartController(DepartService departService, DepartVoMapper departVoMapper) {
    super(departService, departVoMapper);
    this.departService = departService;
    this.departVoMapper = departVoMapper;
  }

  @GetMapping("/get-all-depart-to-tree")
  @ApiOperation("获取指定租户下的depart所有数据，返回树状结构")
  public Result<List<DepartVo>> findAllByQueryCriteriaToTree(@RequestParam("tenantId") String tenantId) {
    DepartFindAllByQueryCriteria departFindAllByQueryCriteria=new DepartFindAllByQueryCriteria();
    departFindAllByQueryCriteria.setTenantId(tenantId);
    return findAllByQueryCriteriaToResultToTree(departFindAllByQueryCriteria);
  }
  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<DepartVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<DepartVo> insert(@RequestBody DepartInsertInput departInsertInput) {
    return insertToResult(departInsertInput);
  }
  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<DepartVo> update(@RequestBody DepartUpdateInput departUpdateInput) {
    return updateToResult(departUpdateInput);
  }
  @ApiOperation("")
  @DeleteMapping
  public Result<DepartVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }
  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/

}
