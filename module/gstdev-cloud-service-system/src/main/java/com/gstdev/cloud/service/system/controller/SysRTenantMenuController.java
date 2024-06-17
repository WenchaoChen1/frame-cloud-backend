// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.service.system.mapper.vo.RTenantMenuVoMapper;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.*;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;
import com.gstdev.cloud.service.system.service.SysRTenantMenuService;
import com.gstdev.cloud.base.definition.domain.Result;

import com.gstdev.cloud.rest.core.controller.POJOController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

//@ResponseBody
@RestController
@RequestMapping("/v1/rTenantMenu")
public class SysRTenantMenuController implements POJOController<RTenantMenu, String,  RTenantMenuVo, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {

    @Resource
    private SysRTenantMenuService rTenantMenuService;

    @Resource
    private RTenantMenuVoMapper rTenantMenuVoMapper;

    @Override
    public SysRTenantMenuService getService() {
        return rTenantMenuService;
    }

    @Override
    public RTenantMenuVoMapper getMapper() {
        return rTenantMenuVoMapper;
    }

//    public SysRTenantMenuController(SysRTenantMenuService rTenantMenuService, RTenantMenuVoMapper rTenantMenuVoMapper) {
//        this.rTenantMenuService = rTenantMenuService;
//        this.rTenantMenuVoMapper = rTenantMenuVoMapper;
//    }

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
        List<RTenantMenuDto> all = getService().findAllToDto((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, rTenantMenuFindAllByQueryCriteria, criteriaBuilder));
        List<String> strings = all.stream().map(RTenantMenuDto::getMenuId).toList();
        return Result.success(strings);
    }


    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
