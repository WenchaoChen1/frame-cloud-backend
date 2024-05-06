// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;


import com.gstdev.cloud.service.system.pojo.base.depart.DepartDto;
import com.gstdev.cloud.service.system.pojo.base.depart.DepartInsertInput;
import com.gstdev.cloud.service.system.pojo.base.depart.DepartUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.Depart;
import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DepartMapper extends BaseTreeMapper<Depart, DepartDto, DepartInsertInput, DepartUpdateInput> {

}
