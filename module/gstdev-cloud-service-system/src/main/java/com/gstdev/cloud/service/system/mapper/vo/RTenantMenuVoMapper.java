// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.*;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuVo;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuVoMapper extends BasePOJOMapper<RTenantMenu, RTenantMenuDto, RTenantMenuVo, RTenantMenuInsertInput, RTenantMenuUpdateInput> {
//    List<RTenantMenuVo> menuDtoToRTenantMenuVo(List<MenuDto> var1);

}

