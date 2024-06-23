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
import com.gstdev.cloud.rest.core.controller.POJOController;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.*;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.InsertTenantMenuIO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.RoleManageTenantMenuTreeQO;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.RoleManageTenantMenuTreeVO;
import com.gstdev.cloud.service.system.mapper.vo.RTenantMenuVoMapper;
import com.gstdev.cloud.service.system.service.SysRTenantMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/rTenantMenu")
public class SysRTenantMenuController implements POJOController<RTenantMenu, String, RTenantMenuVo, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput, RTenantMenuPageQueryCriteria, RTenantMenuFindAllByQueryCriteria> {

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

    @Tag(name = "Tenant Manage")
    @PostMapping("/insert-tenant-menu")
    @Operation(summary = "insert-tenant-menu")
    public Result insertTenantMenu(@RequestBody @Validated InsertTenantMenuIO insertTenantMenuIO) {
        rTenantMenuService.insertTenantMenu(insertTenantMenuIO);
        return result();
    }

    @Tag(name = "Tenant Manage")
    @GetMapping("/get-all-by-tenant-id")
    @Operation(summary = "get-all-by-tenant-id")
    public Result<List<String>> getAllByTenantId(@NotBlank @RequestParam("tenantId") String tenantId) {
        RTenantMenuFindAllByQueryCriteria rTenantMenuFindAllByQueryCriteria = new RTenantMenuFindAllByQueryCriteria();
        rTenantMenuFindAllByQueryCriteria.setTenantId(tenantId);
        List<RTenantMenu> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, rTenantMenuFindAllByQueryCriteria, criteriaBuilder));
        List<String> strings = all.stream()
            .map(RTenantMenu::getMenu).map(SysMenu::getId)
            .toList();

        return Result.success(strings);
    }


    /**
     * 获取指定租户的所有菜单，返回树状结构.租户管理
     *
     * @param
     * @return
     */
    @Tag(name = "Role Manage")
    @GetMapping("/get-role-manage-tenant-menu-tree")
    @Operation(summary = "get-role-manage-tenant-menu-tree")
    public Result<List<RoleManageTenantMenuTreeVO>> getRoleManageTenantMenuTree(RoleManageTenantMenuTreeQO roleManageTenantMenuTreeQO, @NotBlank @RequestParam("tenantId") String tenantId) {
        roleManageTenantMenuTreeQO.setTenantId(tenantId);
        List<RTenantMenu> all = getService().findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, roleManageTenantMenuTreeQO, criteriaBuilder));
        List<SysMenu> list = all.stream().map(RTenantMenu::getMenu).toList();
        List<RoleManageTenantMenuTreeVO> roleManageRTenantMenuTreeVOToTree = getMapper().toRoleManageRTenantMenuTreeVOToTree(list);
        return result(roleManageRTenantMenuTreeVOToTree);
    }
    /*------------------------------------------以上是系统访问控制自定义代码--------------------------------------------*/


}
