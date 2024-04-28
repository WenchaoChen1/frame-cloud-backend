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
import com.frame.template.service.system.pojo.entity.RTenantMenu;
import com.frame.template.service.system.service.MenuService;
import com.frame.template.service.system.service.RTenantMenuService;
import com.gstdev.cloud.base.definition.domain.Result;

import com.gstdev.cloud.rest.core.controller.POJOController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;


@RestController
@RequestMapping("/v1/rTenantMenu")
public class RTenantMenuController implements POJOController<RTenantMenu, String, RTenantMenuService, RTenantMenuVoMapper, RTenantMenuVo, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {

    @Resource
    private RTenantMenuService rTenantMenuService;

    @Resource
    private RTenantMenuVoMapper rTenantMenuVoMapper;
    @Resource
    private MenuService menuService;

    @Override
    public RTenantMenuService getService() {
        return rTenantMenuService;
    }

    @Override
    public RTenantMenuVoMapper getMapper() {
        return rTenantMenuVoMapper;
    }

    public RTenantMenuController(RTenantMenuService rTenantMenuService, RTenantMenuVoMapper rTenantMenuVoMapper, MenuService menuService) {
        this.rTenantMenuService = rTenantMenuService;
        this.rTenantMenuVoMapper = rTenantMenuVoMapper;
        this.menuService = menuService;
    }

    @PostMapping("/insertTenantMenu")
    @Operation(summary = "insertSave")
    public Result<String> insertTenantMenu(@RequestBody RTenantMenuInsertInput rTenantMenuInsertInput) {
        return rTenantMenuService.insertTenantMenu(rTenantMenuInsertInput);
    }

    @GetMapping("/get-all-by-tenant-id")
    @Operation(summary = "获取指定租户的所有菜单，返回id")
    public Result<List<String>> getAllByTenantId(@RequestParam("tenantId") String tenantId) {
        RTenantMenuFindAllByQueryCriteria rTenantMenuFindAllByQueryCriteria = new RTenantMenuFindAllByQueryCriteria();
        rTenantMenuFindAllByQueryCriteria.setTenantId(tenantId);
        List<String> strings = getService().findAllByQueryCriteriaToDto(rTenantMenuFindAllByQueryCriteria).stream().map(RTenantMenuDto::getMenuId).toList();
        return Result.success(strings);
    }


    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
