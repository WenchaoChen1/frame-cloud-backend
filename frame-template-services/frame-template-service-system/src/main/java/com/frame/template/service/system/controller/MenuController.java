// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.controller;

import com.frame.template.common.utils.SecurityUtils;
import com.frame.template.service.system.mapper.vo.MenuVoMapper;
import com.frame.template.service.system.pojo.base.menu.*;
import com.frame.template.service.system.pojo.domain.Menu;
import com.frame.template.service.system.service.MenuService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.service.system.service.RoleService;
import com.gstdev.cloud.rest.core.controller.BaseTreeController;
import com.gstdev.cloud.rest.core.controller.TreeController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@RestController
@RequestMapping("/v1/menu")
public class MenuController implements TreeController<Menu, String, MenuService, MenuVoMapper, MenuVo, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {

    @Resource
    private MenuService menuService;

    @Resource
    private MenuVoMapper menuVoMapper;
    @Resource
    private RoleService roleService;

    public MenuController(MenuService menuService, MenuVoMapper menuVoMapper, RoleService roleService) {
        this.menuService = menuService;
        this.menuVoMapper = menuVoMapper;
        this.roleService = roleService;
    }

    @Override
    public MenuService getService() {
        return menuService;
    }

    @Override
    public MenuVoMapper getMapper() {
        return menuVoMapper;
    }


    @GetMapping("/get-all-menu-to-tree")
    @Operation(summary = "获取所有菜单，返回树状结构")
    public Result<List<MenuVo>> findAllByQueryCriteriaToTree() {
        System.out.println(SecurityUtils.getUserId());
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
