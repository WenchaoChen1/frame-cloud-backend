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
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.TreeController;
import com.gstdev.cloud.service.system.mapper.vo.SysTenantMapper;
import com.gstdev.cloud.service.system.domain.base.tenant.*;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.InsertTenantManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.TenantManageQO;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.UpdateTenantManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.TenantByIdToTreeQO;
import com.gstdev.cloud.service.system.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // ********************************* Tenant Manage *****************************************

    @GetMapping("/get-tenant-manage-page")
    @Operation(summary = "获取所有的用户,分页")
    public Result<Map<String, Object>> getTenantManagePage(TenantManageQO tenantManageQO, BasePage basePage) {
        return this.findByPageToVo((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantManageQO, criteriaBuilder), basePage);
    }

    @GetMapping("/get-tenant-manage-tree")
    @Operation(summary = "获取当前租户以及当前租户的所有子租户，返回树状结构")
    public Result<List<TenantVo>> getTenantManageTree(TenantByIdToTreeQO tenantFindAllByQueryCriteria) {
        if (tenantFindAllByQueryCriteria.getTenantId() != null) {
            List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(tenantFindAllByQueryCriteria.getTenantId());
            List<String> tenantIds = itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
            tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
        }
        return this.result(this.getMapper().toVo(this.getService().findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantFindAllByQueryCriteria, criteriaBuilder))));
    }

    @PostMapping("/insert-tenant-manage")
    @Operation(summary = "新增一条数据")
    public Result insertTenantManage(@RequestBody @Validated InsertTenantManageIO insertTenantManageIO) {
        this.getService().insertAndUpdate(tenantVoMapper.toEntity(insertTenantManageIO));
        return result();
    }

    @PutMapping("/update-tenant-manage")
    @Operation(summary = "新增一条数据")
    public Result updateTenantManage(@RequestBody @Validated UpdateTenantManageIO updateTenantManageIO) {
        SysTenant sysTenant = this.getService().findById(updateTenantManageIO.getId());
        tenantVoMapper.copy(updateTenantManageIO, sysTenant);
        this.getService().insertAndUpdate(sysTenant);
        return result();
    }

    @GetMapping("/get-tenant-manage-detail/{id}")
    @Operation(summary = "get-tenant-manage-detail")
    public Result<TenantVo> getTenantManageDetail(@PathVariable String id) {
        return findByIdToResult(id);
    }

    @Operation(summary = "删除一条数据")
    @DeleteMapping("delete-tenant-manage/{id}")
    public Result deleteTenantManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Operation(summary = "删除多条数据")
    @DeleteMapping("delete-all-tenant-manage")
    public Result deleteAllTenantManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }


    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/

    //    public SysTenantController(SysTenantService tenantService, TenantVoMapper tenantVoMapper) {
//        this.tenantService = tenantService;
//        this.tenantVoMapper = tenantVoMapper;
//    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }
//
//
//    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<TenantVo> getById(String id) {
//        return findByIdToResult(id);
//    }
//
//    @Operation(summary = "")
//    @DeleteMapping
//    public Result<TenantVo> deleteById(String id) {
//        return deleteByIdToResult(id);
//    }

}

