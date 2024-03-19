// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.mapper;

import com.gstdev.template.common.base.baseTree.BaseTreeMapper;
import com.gstdev.template.service.system.pojo.domain.Role;
import com.gstdev.template.service.system.pojo.base.role.RoleDto;
import com.gstdev.template.service.system.pojo.base.role.RoleInsertInput;
import com.gstdev.template.service.system.pojo.base.role.RoleUpdateInput;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhucy
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RoleMapper extends BaseTreeMapper<Role, RoleDto, RoleInsertInput, RoleUpdateInput> {
}

