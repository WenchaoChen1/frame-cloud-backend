// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.service.system.pojo.base.depart.DepartDto;
import com.gstdev.cloud.service.system.pojo.base.depart.DepartVo;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.gstdev.cloud.service.system.pojo.base.depart.DepartDto;
import com.gstdev.cloud.service.system.pojo.base.depart.DepartVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DepartVoMapper extends BaseTreeVoMapper<DepartVo, DepartDto> {

}

