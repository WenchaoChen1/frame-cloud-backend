// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.TenantVoMapper;
import com.frame.template.service.system.pojo.base.tenant.*;
import com.frame.template.service.system.service.TenantService;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.BaseRedisCurrentLoginInformation;
import com.frame.template.common.base.baseTree.BaseTreeController;

import com.frame.template.service.system.pojo.base.tenant.*;
import com.frame.template.service.system.pojo.base.tenant.TenantVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/v1/tenant")
public class TenantController extends BaseTreeController<TenantService, TenantVoMapper, TenantVo, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {


  @Resource
  private TenantService tenantService;

  @Resource
  private TenantVoMapper tenantVoMapper;

  @Resource
  private BaseRedisCurrentLoginInformation redisCurrentLoginInformation;

  public TenantController(TenantService tenantService, TenantVoMapper tenantVoMapper) {
    super(tenantService, tenantVoMapper);
    this.tenantService = tenantService;
    this.tenantVoMapper = tenantVoMapper;
  }

//  @GetMapping("/get-all-tenant-to-tree")
//  @ApiOperation("获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

  @GetMapping("/get-tenant-by-id-to-tree")
  @ApiOperation("获取当前租户以及当前租户的所有子租户，返回树状结构")
  public Result<List<TenantVo>> findByIdToTreeToResult(TenantFindAllByQueryCriteria tenantFindAllByQueryCriteria) {
    List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(redisCurrentLoginInformation.getCurrentLoginTenantId());
    List<String> tenantIds =itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
    tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
    return findAllByQueryCriteriaToResultToTree(tenantFindAllByQueryCriteria);
  }

  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<TenantVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<TenantVo> insert(@RequestBody @Validated TenantInsertInput tenantInsertInput) {
    return insertToResult(tenantInsertInput);
  }

  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<TenantVo> update(@RequestBody @Validated TenantUpdateInput updateInput) {
    return updateToResult(updateInput);
  }

  @ApiOperation("")
  @DeleteMapping
  public Result<TenantVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }

  /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}

