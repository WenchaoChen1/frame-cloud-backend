// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.MenuVoMapper;
import com.frame.template.service.system.pojo.base.menu.*;
import com.frame.template.service.system.service.MenuService;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeController;
import com.frame.template.service.system.pojo.base.menu.*;
import com.frame.template.service.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/menu")
public class MenuController extends BaseTreeController<MenuService, MenuVoMapper, MenuVo, MenuDto, MenuInsertInput, MenuUpdateInput, MenuPageQueryCriteria, MenuFindAllByQueryCriteria> {

  @Resource
  private MenuService menuService;

  @Resource
  private MenuVoMapper menuVoMapper;
  @Resource
  private RoleService roleService;

  public MenuController(MenuService menuService, MenuVoMapper menuVoMapper) {
    super(menuService, menuVoMapper);
    this.menuService = menuService;
    this.menuVoMapper = menuVoMapper;
  }

  @GetMapping("/get-all-mean-to-tree")
  @ApiOperation("获取所有菜单，返回树状结构")
  public Result<List<MenuVo>> findAllByQueryCriteriaToTree() {
    MenuFindAllByQueryCriteria menuFindAllByQueryCriteria=new MenuFindAllByQueryCriteria();
    return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
  }
  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<MenuVo> getById(String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<MenuVo> insert(@RequestBody MenuInsertInput menuInsertInput) {
    return insertToResult(menuInsertInput);
  }

  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<MenuVo> update(@RequestBody MenuUpdateInput menuUpdateInput) {
    return updateToResult(menuUpdateInput);
  }
  @ApiOperation("")
  @DeleteMapping
  public Result<MenuVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }


  @GetMapping("/get-all-by-tenant-Menu-to-tree")
  @ApiOperation("获取指定租户的所有菜单，返回树状结构")
  public Result<List<MenuVo>> getAllByTenantMenuToTree(@RequestParam("tenantId") String tenantId) {
    MenuFindAllByQueryCriteria menuFindAllByQueryCriteria=new MenuFindAllByQueryCriteria();
    menuFindAllByQueryCriteria.setTenantId(tenantId);
    return findAllByQueryCriteriaToResultToTree(menuFindAllByQueryCriteria);
  }
  @GetMapping("/get-all-by-role-Menu-to-tree")
  @ApiOperation("获取指定role的所有菜单，返回树状结构")
  public Result<List<MenuVo>> getAllByRoleMenuToTree(@RequestParam("roleId") String roleId) {
    return getMapper().toAllVo(getService().getAllByRoleMenuToTree(roleId));
  }
  @GetMapping("/get-all-tenant-menu-id")
  @ApiOperation("获取指定租户的所有菜单id")
  public Result<MenuVo> getAllTenantMenuIds(@RequestParam("tenantId") String tenantId) {
    return getMapper().toVo(getService().getAllTenantMenuIds(tenantId));
  }

  /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/



}
