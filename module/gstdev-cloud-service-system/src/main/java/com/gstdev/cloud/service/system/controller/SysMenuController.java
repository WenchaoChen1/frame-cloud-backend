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
import com.gstdev.cloud.service.system.domain.base.menu.*;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysMenu.*;
import com.gstdev.cloud.service.system.mapper.vo.SysMenuMapper;
import com.gstdev.cloud.service.system.service.SysMenuService;
import com.gstdev.cloud.service.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ResponseBody
@RestController
@RequestMapping("/v1/menu")
public class SysMenuController implements TreeController<SysMenu, String, MenuVo, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {

    @Resource
    private SysMenuService menuService;

    @Resource
    private SysMenuMapper menuMapper;

    @Resource
    private SysRoleService roleService;

    @Override
    public SysMenuService getService() {
        return menuService;
    }

    @Override
    public SysMenuMapper getMapper() {
        return menuMapper;
    }

    // ********************************* menu Manage *****************************************

    @GetMapping("/get-menu-manage-tree")
    @Operation(summary = "获取所有菜单，返回树状结构")
    public Result<List<MenuManageTreeVo>> getMenuManageMageTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }

    @GetMapping("/get-menu-manage-detail/{id}")
    @Operation(summary = "get-menu-manage-detail")
    public Result<MenuManageDetailVo> getMenuManageDetail(@PathVariable String id) {
        return result(this.getMapper().toMenuManageDetailVo(getService().findById(id)));
    }

    @PostMapping("/insert-menu-manage")
    @Operation(summary = "insert-menu-manage")
    public Result insertAMenuManage(@RequestBody @Validated InsertMenuManageIO insertMenuManageIO) {
        this.getService().insertAndUpdate(menuMapper.toEntity(insertMenuManageIO));
        return result();
    }

    @PutMapping("/update-menu-manage")
    @Operation(summary = "update-menu-manage")
    public Result updateMenuManage(@RequestBody @Validated UpdateMenuManageIO updateMenuManageIO) {
        SysMenu sysMenu = this.getService().findById(updateMenuManageIO.getId());
        menuMapper.copy(updateMenuManageIO, sysMenu);
        this.getService().insertAndUpdate(sysMenu);
        return result();
    }

    @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-menu-manage/{id}")
    public Result deleteMenuManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-menu-manage")
    public Result deleteAllMenuManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }

    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/

    //    public SysMenuController(SysMenuService menuService, MenuVoMapper menuVoMapper, SysRoleService roleService) {
//        this.menuService = menuService;
//        this.menuVoMapper = menuVoMapper;
//        this.roleService = roleService;
//    }
    //    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<MenuVo> getById(String id) {
//        return findByIdToResult(id);
//    }

//    @PostMapping
//    @Operation(summary = "新增一条数据")
//    public Result<MenuVo> insert(@RequestBody MenuInsertInput menuInsertInput) {
//        return insertToResult(menuInsertInput);
//    }
//
//    @PutMapping
//    @Operation(summary = "修改一条数据")
//    public Result<MenuVo> update(@RequestBody MenuUpdateInput menuUpdateInput) {
//        return updateToResult(menuUpdateInput);
//    }
    @Tag(name = "Tenant Manage")
    @GetMapping("/get-tenant-manage-menu-tree")
    @Operation(summary = "获取所有菜单，返回树状结构")
    public Result<List<MenuManageTreeVo>> getTenantManageMenuTree(MenuManageTreeQO queryCriteria) {
        List<SysMenu> byPage = this.getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder));
        return this.result(this.getMapper().toMenuManageTreeVoToTree(byPage));
    }


    @Operation(summary = "")
    @DeleteMapping
    public Result<MenuVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }

    @Tag(name = "Role Manage")
    @GetMapping("/get-all-by-tenant-menu-to-tree")
    @Operation(summary = "获取指定租户的所有菜单，返回树状结构")
    public Result<List<MenuVo>> getAllByTenantMenuToTree(@NotBlank @RequestParam("tenantId") String tenantId) {
        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
        menuFindAllByQueryCriteria.setTenantId(tenantId);
        return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
    }

    @GetMapping("/get-all-by-role-menu-to-tree")
    @Operation(summary = "获取指定role的所有菜单，返回树状结构")
    public Result<List<MenuVo>> getAllByRoleMenuToTree(@NotBlank @RequestParam("roleId") String roleId) {
        return getMapper().toAllVo(getService().getAllByRoleMenuToTree(roleId));
    }
//    @GetMapping("/get-all-tenant-menu-id/{tenantId}")
//    @Operation(summary = "获取指定租户的所有菜单")
//    public Result<MenuVo> getAllTenantMenuIds(@NotBlank @RequestParam("tenantId") String tenantId) {
//        return getMapper().toVo(getService().getAllTenantMenuIds(tenantId));
//    }

}
