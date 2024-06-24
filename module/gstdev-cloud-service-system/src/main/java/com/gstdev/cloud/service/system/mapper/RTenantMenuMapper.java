// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.TreeUtils;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.*;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuVo;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.pojo.rTenantMenu.RoleManageTenantMenuTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuMapper extends BasePOJOMapper<RTenantMenu, RTenantMenuDto, RTenantMenuVo, RTenantMenuInsertInput, RTenantMenuUpdateInput> {
    List<RoleManageTenantMenuTreeVO> toRoleManageRTenantMenuTreeVO(List<SysMenu> sysRole);

    default List<RoleManageTenantMenuTreeVO> toRoleManageRTenantMenuTreeVOToTree(List<SysMenu> sysRole) {
        List<RoleManageTenantMenuTreeVO> roleManageRoleDetaiToListVo = toRoleManageRTenantMenuTreeVO(sysRole);
        return TreeUtils.buildTree(
            roleManageRoleDetaiToListVo,
            RoleManageTenantMenuTreeVO::getId,
            RoleManageTenantMenuTreeVO::getParentId,
            Comparator.comparingInt((RoleManageTenantMenuTreeVO item) ->
                item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
    }

}

