// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper;


import com.frame.template.service.system.pojo.base.role.RoleDto;
import com.frame.template.service.system.pojo.base.role.RoleInsertInput;
import com.frame.template.service.system.pojo.base.role.RoleUpdateInput;
import com.frame.template.service.system.pojo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;

/**
 * @author zhucy
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RoleMapper extends BaseTreeMapper<Role, RoleDto, RoleInsertInput, RoleUpdateInput> {
}

