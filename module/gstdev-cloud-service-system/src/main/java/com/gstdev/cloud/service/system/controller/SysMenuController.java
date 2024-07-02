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
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.*;
import com.gstdev.cloud.service.system.mapper.SysMenuMapper;
import com.gstdev.cloud.service.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/menu")
public class SysMenuController implements ResultController {

    @Resource
    @Lazy
    private SysMenuService menuService;

    @Resource
    private SysMenuMapper menuMapper;

    public SysMenuService getService() {
        return menuService;
    }

    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    // ********************************* menu Manage *****************************************
    @Tag(name = "Tenant Manage")
    @GetMapping("/get-menu-manage-tree")
    @Operation(summary = "get-menu-manage-tree")
    public Result<List<MenuManageTreeVo>> getMenuManageMageTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-menu-manage-detail/{id}")
    @Operation(summary = "get-menu-manage-detail")
    public Result<MenuManageDetailVo> getMenuManageDetail(@PathVariable String id) {
        return result(this.getMapper().toMenuManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-menu-manage")
    @Operation(summary = "insert-menu-manage")
    public Result<String> insertMenuManage(@RequestBody @Validated InsertMenuManageIO insertMenuManageIO) {
        this.getService().insertMenuManage(insertMenuManageIO);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @PutMapping("/update-menu-manage")
    @Operation(summary = "update-menu-manage")
    public Result<String> updateMenuManage(@RequestBody @Validated UpdateMenuManageIO updateMenuManageIO) {
        this.getService().updateMenuManage(updateMenuManageIO);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-menu-manage")
    @DeleteMapping("/delete-menu-manage/{id}")
    public Result<String> deleteMenuManage(@PathVariable String id) {
        getService().deleteById(id);
        return Result.success();
    }

    @Tag(name = "Tenant Manage")
    @Operation(summary = "delete-all-menu-manage")
    @DeleteMapping("/delete-all-menu-manage")
    public Result<String> deleteAllMenuManage(List<String> id) {
        getService().deleteAllById(id);
        return Result.success();
    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-menu-tree")
    @Operation(summary = "get-tenant-manage-menu-tree获取所有菜单，返回树状结构")
    public Result<List<MenuManageTreeVo>> getTenantManageMenuTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }

//    /**
//     * 获取指定租户的所有菜单，返回树状结构
//     * @param tenantId
//     * @return
//     */
//    @Tag(name = "Role Manage")
//    @GetMapping("/get-all-by-tenant-menu-to-tree")
//    @Operation(summary = "get-all-by-tenant-menu-to-tree")
//    public Result<List<MenuVo>> getAllByTenantMenuToTree(@NotBlank @RequestParam("tenantId") String tenantId) {
//        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
//        menuFindAllByQueryCriteria.setTenantId(tenantId);
//        return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
//    }
//
//    @GetMapping("/get-all-by-role-menu-to-tree")
//    @Operation(summary = "/get-all-by-role-menu-to-tree")
//    public Result<List<MenuVo>> getAllByRoleMenuToTree(@NotBlank @RequestParam("roleId") String roleId) {
//        return getMapper().toAllVo(getService().getAllByRoleMenuToTree(roleId));
//    }
//    @GetMapping("/get-all-tenant-menu-id/{tenantId}")
//    @Operation(summary = "获取指定租户的所有菜单")
//    public Result<MenuVo> getAllTenantMenuIds(@NotBlank @RequestParam("tenantId") String tenantId) {
//        return getMapper().toVo(getService().getAllTenantMenuIds(tenantId));
//    }

}
