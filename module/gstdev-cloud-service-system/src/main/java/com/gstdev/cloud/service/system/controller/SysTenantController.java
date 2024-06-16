// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.TreeController;
import com.gstdev.cloud.service.system.mapper.vo.SysTenantMapper;
import com.gstdev.cloud.service.system.pojo.base.tenant.*;
import com.gstdev.cloud.service.system.pojo.entity.SysTenant;
import com.gstdev.cloud.service.system.pojo.o.sysTenant.InsertTenantManageIO;
import com.gstdev.cloud.service.system.pojo.o.sysTenant.UpdateTenantManageIO;
import com.gstdev.cloud.service.system.pojo.o.sysTenant.TenantByIdToTreeQO;
import com.gstdev.cloud.service.system.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ResponseBody
@RestController
@RequestMapping("/v1/tenant")
public class SysTenantController implements TreeController<SysTenant, String, TenantVo, TenantDto, TenantInsertInput, TenantUpdateInput, TenantPageQueryCriteria, TenantFindAllByQueryCriteria> {


    @Resource
    private SysTenantService tenantService;

    @Resource
    private SysTenantMapper tenantVoMapper;

    @Override
    public SysTenantService getService() {
        return tenantService;
    }

    @Override
    public SysTenantMapper getMapper() {
        return tenantVoMapper;
    }

//    public SysTenantController(SysTenantService tenantService, TenantVoMapper tenantVoMapper) {
//        this.tenantService = tenantService;
//        this.tenantVoMapper = tenantVoMapper;
//    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

    @GetMapping("/get-tenant-by-id-to-tree")
    @Operation(summary = "获取当前租户以及当前租户的所有子租户，返回树状结构")
    public Result<List<TenantVo>> findByIdToTreeToResult(TenantByIdToTreeQO tenantFindAllByQueryCriteria) {
        if (tenantFindAllByQueryCriteria.getTenantId() != null){
            List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(tenantFindAllByQueryCriteria.getTenantId());
            List<String> tenantIds = itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
            tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
        }
        return this.result(this.getMapper().toVo(this.getService().findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> {
            return QueryUtils.getPredicate(root, tenantFindAllByQueryCriteria, criteriaBuilder);
        })));
//        return findAllByQueryCriteriaToResultToTree(tenantFindAllByQueryCriteria);
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<TenantVo> getById(String id) {
        return findByIdToResult(id);
    }

    @Operation(summary = "")
    @DeleteMapping
    public Result<TenantVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }

    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
    // ********************************* Tenant Manage *****************************************
//    @GetMapping("/get-tenant-manage-page")
//    @Operation(summary = "获取所有的用户,分页")
//    public Result<Map<String, Object>> getTenantManagePage(TenantManageQO tenantManageQO, Pageable pageable) {
//        return findByPageToVo((root, criteriaQuery, criteriaBuilder) -> {
//            return QueryUtils.getPredicate(root, tenantManageQO, criteriaBuilder);
//        }, pageable);
//    }

    @PostMapping("/insert-tenant-manage")
    @Operation(summary = "新增一条数据")
    public Result insertTenantManage(@RequestBody @Validated InsertTenantManageIO insertTenantManageIO) {
        this.getService().insertAndUpdate(tenantVoMapper.toEntity(insertTenantManageIO));
        return result();
    }
    @PostMapping("/update-tenant-manage")
    @Operation(summary = "新增一条数据")
    public Result updateTenantManage(@RequestBody @Validated UpdateTenantManageIO updateTenantManageIO) {
        SysTenant sysTenant = this.getService().findById(updateTenantManageIO.getId());
        tenantVoMapper.copy(updateTenantManageIO, sysTenant);
        this.getService().insertAndUpdate(sysTenant);
        return result();
    }

}

