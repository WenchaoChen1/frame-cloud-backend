// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.service.system.mapper.vo.MenuVoMapper;
import com.gstdev.cloud.service.system.pojo.base.menu.*;
import com.gstdev.cloud.service.system.pojo.entity.SysMenu;
import com.gstdev.cloud.service.system.service.SysMenuService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.service.SysRoleService;
import com.gstdev.cloud.rest.core.controller.TreeController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;
//@ResponseBody
@RestController
@RequestMapping("/v1/menu")
public class SysMenuController implements TreeController<SysMenu, String, MenuVo, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {

    @Resource
    private SysMenuService menuService;

    @Resource
    private MenuVoMapper menuVoMapper;

    @Resource
    private SysRoleService roleService;

//    public SysMenuController(SysMenuService menuService, MenuVoMapper menuVoMapper, SysRoleService roleService) {
//        this.menuService = menuService;
//        this.menuVoMapper = menuVoMapper;
//        this.roleService = roleService;
//    }

    @Override
    public SysMenuService getService() {
        return menuService;
    }

    @Override
    public MenuVoMapper getMapper() {
        return menuVoMapper;
    }


    @GetMapping("/get-all-menu-to-tree")
    @Operation(summary = "获取所有菜单，返回树状结构")
    public Result<List<MenuVo>> findAllByQueryCriteriaToTree() {
        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
        return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<MenuVo> getById(String id) {
        return findByIdToResult(id);
    }

    @PostMapping
    @Operation(summary = "新增一条数据")
    public Result<MenuVo> insert(@RequestBody MenuInsertInput menuInsertInput) {
        return insertToResult(menuInsertInput);
    }

    @PutMapping
    @Operation(summary = "修改一条数据")
    public Result<MenuVo> update(@RequestBody MenuUpdateInput menuUpdateInput) {
        return updateToResult(menuUpdateInput);
    }

    @Operation(summary = "")
    @DeleteMapping
    public Result<MenuVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }


    @GetMapping("/get-all-by-tenant-menu-to-tree")
    @Operation(summary = "获取指定租户的所有菜单，返回树状结构")
    public Result<List<MenuVo>> getAllByTenantMenuToTree(@RequestParam("tenantId") String tenantId) {
        MenuFindAllByQueryCriteria menuFindAllByQueryCriteria = new MenuFindAllByQueryCriteria();
        menuFindAllByQueryCriteria.setTenantId(tenantId);
        return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
    }

    @GetMapping("/get-all-by-role-menu-to-tree")
    @Operation(summary = "获取指定role的所有菜单，返回树状结构")
    public Result<List<MenuVo>> getAllByRoleMenuToTree(@RequestParam("roleId") String roleId) {
        return getMapper().toAllVo(getService().getAllByRoleMenuToTree(roleId));
    }

    @GetMapping("/get-all-tenant-menu-id")
    @Operation(summary = "获取指定租户的所有菜单id")
    public Result<MenuVo> getAllTenantMenuIds(@RequestParam("tenantId") String tenantId) {
        return getMapper().toVo(getService().getAllTenantMenuIds(tenantId));
    }


    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
