// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper;

import com.frame.template.common.base.baseTree.BaseTreeMapper;
import com.frame.template.service.system.pojo.base.menu.MenuDto;
import com.frame.template.service.system.pojo.base.menu.MenuInsertInput;
import com.frame.template.service.system.pojo.base.menu.MenuUpdateInput;
import com.frame.template.service.system.pojo.domain.Menu;
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
public interface MenuMapper extends BaseTreeMapper<Menu, MenuDto, MenuInsertInput, MenuUpdateInput> {
}

