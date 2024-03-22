// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.RTenantMenuVoMapper;
import com.frame.template.service.system.pojo.base.rTenantMenu.*;
import com.frame.template.service.system.service.MenuService;
import com.frame.template.service.system.service.RTenantMenuService;
import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.base.BaseController;
import com.frame.template.service.system.pojo.base.rTenantMenu.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/v1/rTenantMenu")
public class RTenantMenuController extends BaseController<RTenantMenuService, RTenantMenuVoMapper, RTenantMenuVo, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {

  @Resource
  private RTenantMenuService rTenantMenuService;

  @Resource
  private RTenantMenuVoMapper rTenantMenuVoMapper;
  @Resource
  private MenuService menuService;

  public RTenantMenuController(RTenantMenuService rTenantMenuService, RTenantMenuVoMapper rTenantMenuVoMapper) {
    super(rTenantMenuService, rTenantMenuVoMapper);
    this.rTenantMenuService = rTenantMenuService;
    this.rTenantMenuVoMapper = rTenantMenuVoMapper;
  }


  @PostMapping("/insertTenantMenu")
  @ApiOperation("insertSave")
  public Result<String> insertTenantMenu(@RequestBody RTenantMenuInsertInput rTenantMenuInsertInput) {
      return rTenantMenuService.insertTenantMenu(rTenantMenuInsertInput);
  }
  @GetMapping("/get-all-by-tenant-id")
  @ApiOperation("获取指定租户的所有菜单，返回id")
  public Result<List<String>> getAllByTenantId(@RequestParam("tenantId") String tenantId) {
    RTenantMenuFindAllByQueryCriteria rTenantMenuFindAllByQueryCriteria=new RTenantMenuFindAllByQueryCriteria();
    rTenantMenuFindAllByQueryCriteria.setTenantId(tenantId);
    List<String> strings = getService().findAllByQueryCriteriaToDto(rTenantMenuFindAllByQueryCriteria).stream().map(RTenantMenuDto::getMenuId).toList();
    return Result.success(strings);
  }
  /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
