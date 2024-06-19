// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.TreeBuilder;
import com.gstdev.cloud.service.system.TreeNode;
import com.gstdev.cloud.service.system.domain.base.role.RoleDto;
import com.gstdev.cloud.service.system.domain.base.role.RoleInsertInput;
import com.gstdev.cloud.service.system.domain.base.role.RoleUpdateInput;
import com.gstdev.cloud.service.system.domain.base.role.RoleVo;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.*;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysRoleMapper extends BaseTreeMapper<SysRole, RoleDto, RoleVo, RoleInsertInput, RoleUpdateInput> {
    SysRole toEntity(InsertRoleManageIO insertRoleManageIO);
    RoleManageDetailVo toRoleManageDetailVo(SysRole sysRole);

    List<TreeNode<RoleManageDetailRoleVo>> toRoleManageDetailRoleTreeNodeVo(List<TreeNode<SysRole>> sysRole);

    default List<TreeNode<RoleManageDetailRoleVo>> toRoleManageDetailRoleTreeNodeVoDefault(List<SysRole> sysRole) {
        List<TreeNode<SysRole>> tree = TreeBuilder.buildTree(
            sysRole,
            SysRole::getId,
            SysRole::getParentId,
            Comparator.comparingInt((SysRole item) -> item.getSort() != null ? item.getSort() : Integer.MAX_VALUE)
        );
        return this.toRoleManageDetailRoleTreeNodeVo(tree);
    }
    List<RoleManageTreeVo> toRoleManageTreeVo(List<RoleDto> sysRole);
    List<RoleManagePageVo> toRoleManagePageVo(List<SysRole> sysRole);
    default Page<RoleManagePageVo> toRoleManagePageVo(Page<SysRole> page) {
        List<RoleManagePageVo> responses = this.toRoleManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }

    void copy(UpdateRoleManageIO updateRoleManageIO, @MappingTarget SysRole sysRole);

}

