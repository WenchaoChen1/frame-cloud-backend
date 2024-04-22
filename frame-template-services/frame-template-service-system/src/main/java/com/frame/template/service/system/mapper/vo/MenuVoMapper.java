// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import com.frame.template.service.system.pojo.base.menu.MenuDto;
import com.frame.template.service.system.pojo.base.menu.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface MenuVoMapper extends BaseTreeVoMapper<MenuVo, MenuDto> {


}

