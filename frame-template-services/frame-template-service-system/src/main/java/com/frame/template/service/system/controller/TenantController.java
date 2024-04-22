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
import com.frame.template.service.system.pojo.domain.Tenant;
import com.frame.template.service.system.service.TenantService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.base.BaseRedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.tenant.TenantVo;
import com.gstdev.cloud.rest.core.controller.BaseTreeController;
import com.gstdev.cloud.rest.core.controller.TreeController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/v1/tenant")
public class TenantController implements TreeController<Tenant, String, TenantService, TenantVoMapper, TenantVo, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {


    @Resource
    private TenantService tenantService;

    @Resource
    private TenantVoMapper tenantVoMapper;

    @Resource
    private BaseRedisCurrentLoginInformation redisCurrentLoginInformation;

    @Override
    public TenantService getService() {
        return tenantService;
    }

    @Override
    public TenantVoMapper getMapper() {
        return tenantVoMapper;
    }

    public TenantController(TenantService tenantService, TenantVoMapper tenantVoMapper, BaseRedisCurrentLoginInformation redisCurrentLoginInformation) {
        this.tenantService = tenantService;
        this.tenantVoMapper = tenantVoMapper;
        this.redisCurrentLoginInformation = redisCurrentLoginInformation;
    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

    @GetMapping("/get-tenant-by-id-to-tree")
    @Operation(summary = "获取当前租户以及当前租户的所有子租户，返回树状结构")
    public Result<List<TenantVo>> findByIdToTreeToResult(TenantFindAllByQueryCriteria tenantFindAllByQueryCriteria) {
        List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(redisCurrentLoginInformation.getCurrentLoginTenantId());
        List<String> tenantIds = itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
        tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
        return findAllByQueryCriteriaToResultToTree(tenantFindAllByQueryCriteria);
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<TenantVo> getById(String id) {
        return findByIdToResult(id);
    }

    @PostMapping
    @Operation(summary = "新增一条数据")
    public Result<TenantVo> insert(@RequestBody @Validated TenantInsertInput tenantInsertInput) {
        return insertToResult(tenantInsertInput);
    }

    @PutMapping
    @Operation(summary = "修改一条数据")
    public Result<TenantVo> update(@RequestBody @Validated TenantUpdateInput updateInput) {
        return updateToResult(updateInput);
    }

    @Operation(summary = "")
    @DeleteMapping
    public Result<TenantVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }


    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/


}

