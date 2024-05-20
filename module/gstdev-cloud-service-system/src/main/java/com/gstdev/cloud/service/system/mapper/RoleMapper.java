// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;


import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.service.system.pojo.base.role.RoleDto;
import com.gstdev.cloud.service.system.pojo.base.role.RoleInsertInput;
import com.gstdev.cloud.service.system.pojo.base.role.RoleUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.role.RoleVo;
import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RoleMapper extends BaseDtoMapper<SysRole, RoleDto> {
}

