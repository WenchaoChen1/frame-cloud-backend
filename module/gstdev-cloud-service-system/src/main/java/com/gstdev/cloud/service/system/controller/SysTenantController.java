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
import com.gstdev.cloud.service.system.domain.base.tenant.*;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.*;
import com.gstdev.cloud.service.system.mapper.vo.SysTenantMapper;
import com.gstdev.cloud.service.system.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
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

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-page")
    @Operation(summary = "get-tenant-manage-page")
    public Result<Map<String, Object>> getTenantManagePage(TenantManageQO tenantManageQO, BasePage basePage) {
        Page<SysTenant> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantManageQO, criteriaBuilder), basePage);
        return this.result(this.getMapper().toTenantManagePageVo(byPage));
    }

    /**
     * 获取当前租户以及当前租户的所有子租户，返回树状结构
     * @param tenantFindAllByQueryCriteria
     * @return
     */
    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-tree")
    @Operation(summary = "get-tenant-manage-tree")
    public Result<List<TenantManageTreeVo>> getTenantManageTree(TenantManageTreeQO tenantFindAllByQueryCriteria) {
        if (tenantFindAllByQueryCriteria.getTenantId() != null) {
            List<TenantDto> itselfAndSubsetsToDto = getService().findItselfAndSubsetsToDto(tenantFindAllByQueryCriteria.getTenantId());
            List<String> tenantIds = itselfAndSubsetsToDto.stream().map(TenantDto::getId).toList();
            tenantFindAllByQueryCriteria.setTenantIds(tenantIds);
        }
        return this.result(this.getMapper().toTenantManageTreeVo(this.getService().findAllByQueryCriteriaToDtoToTree((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, tenantFindAllByQueryCriteria, criteriaBuilder))));
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-detail/{id}")
    @Operation(summary = "get-tenant-manage-detail")
    public Result<TenantManageDetailVo> getTenantManageDetail(@PathVariable String id) {
        return result(this.getMapper().toTenantManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-tenant-manage")
    @Operation(summary = "insert-tenant-manage")
    public Result insertTenantManage(@RequestBody @Validated InsertTenantManageIO insertTenantManageIO) {
        this.getService().insertAndUpdate(tenantVoMapper.toEntity(insertTenantManageIO));
        return result();
    }

    @Tag(name = "Tenant Manage")
    @PutMapping("/update-tenant-manage")
    @Operation(summary = "update-tenant-manage")
    public Result updateTenantManage(@RequestBody @Validated UpdateTenantManageIO updateTenantManageIO) {
        SysTenant sysTenant = this.getService().findById(updateTenantManageIO.getId());
        tenantVoMapper.copy(updateTenantManageIO, sysTenant);
        this.getService().insertAndUpdate(sysTenant);
        return result();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-tenant-manage")
    @DeleteMapping("/delete-tenant-manage/{id}")
    public Result deleteTenantManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-all-tenant-manage")
    @DeleteMapping("/delete-all-tenant-manage")
    public Result deleteAllTenantManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }


    // ********************************* other Manage *****************************************


    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-tenant-detail-to-list")
    @Operation(summary = "get-role-manage-tenant-detail-to-list")
    public Result<List<RoleManageTenantDetaiToListVo>> getRoleManageTenantDetaiToListAll(RoleManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toRoleManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "User Manage")
    @GetMapping("/get-user-manage-tenant-detail-to-list")
    @Operation(summary = "get-account-manage-tenant-detail-to-list")
    public Result<List<UserManageTenantDetaiToListVo>> getUserManageTenantDetaiToListAll(UserManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toUserManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "Account Manage")
    @GetMapping("/get-account-manage-tenant-detail-to-list")
    @Operation(summary = "get-account-manage-tenant-detail-to-list")
    public Result<List<AccountManageTenantDetaiToListVo>> getAccountManageTenantDetaiToListAll(AccountManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toAccountManageTenantDetaiToListVoToTree(all));
    }

    @Tag(name = "Menu Manage")
    @GetMapping("/get-menu-manage-tenant-detail-to-list")
    @Operation(summary = "get-menu-manage-tenant-detail-to-list")
    public Result<List<MenuManageTenantDetaiToListVo>> getMenuManageTenantDetaiToListAll(MenuManageTenantDetaiToListQO queryCriteria) {
        List<SysTenant> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return result(getMapper().toMenuManageTenantDetaiToListVoToTree(all));
    }
//  @GetMapping("/get-all-tenant-to-tree")
//  @Operation(summary = "获取当前当前租户的所有子租户，返回树状结构")
//  public Result<List<TenantVo>> findAllByQueryCriteriaToTree() {
//    return findByParentIdIdToTreeToResult(redisCurrentLoginInformation.getCurrentLoginTenantId());
//  }

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

