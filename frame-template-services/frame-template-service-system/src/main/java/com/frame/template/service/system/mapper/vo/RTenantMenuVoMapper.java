// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.mapper.vo;

import com.frame.template.common.base.BaseVoMapper;
import com.frame.template.service.system.pojo.base.menu.MenuDto;
import com.frame.template.service.system.pojo.base.rTenantMenu.RTenantMenuDto;
import com.frame.template.service.system.pojo.base.rTenantMenu.RTenantMenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuVoMapper extends BaseVoMapper<RTenantMenuVo, RTenantMenuDto> {
  List<RTenantMenuVo> menuDtoToRTenantMenuVo(List<MenuDto> var1);

}

