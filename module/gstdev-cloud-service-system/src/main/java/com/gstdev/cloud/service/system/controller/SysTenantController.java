// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.service.system.mapper.vo.TenantVoMapper;
import com.gstdev.cloud.service.system.pojo.base.tenant.*;
import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.service.system.service.SysTenantService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.controller.TreeController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/v1/tenant")
public class SysTenantController implements TreeController<Tenant, String, TenantVo, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {


    @Resource
    private SysTenantService tenantService;

    @Resource
    private TenantVoMapper tenantVoMapper;

    @Override
    public SysTenantService getService() {
        return tenantService;
    }

    @Override
    public TenantVoMapper getMapper() {
        return tenantVoMapper;
    }

    public SysTenantController(SysTenantService tenantService, TenantVoMapper tenantVoMapper) {
        this.tenantService = tenantService;
        this.tenantVoMapper = tenantVoMapper;
    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

    @GetMapping("/get-tenant-by-id-to-tree")
    @Operation(summary = "获取当前租户以及当前租户的所有子租户，返回树状结构")
    public Result<List<TenantVo>> findByIdToTreeToResult(TenantFindAllByQueryCriteria tenantFindAllByQueryCriteria) {
        if (tenantFindAllByQueryCriteria.getTenantId() != null){
            List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(tenantFindAllByQueryCriteria.getTenantId());
            List<String> tenantIds = itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
            tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
        }
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

