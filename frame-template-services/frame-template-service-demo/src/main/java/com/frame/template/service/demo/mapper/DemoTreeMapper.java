// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.mapper;


import com.frame.template.common.base.baseTree.BaseTreeMapper;
import com.frame.template.service.demo.pojo.base.demoTree.DemoTreeDto;
import com.frame.template.service.demo.pojo.base.demoTree.DemoTreeInsertInput;
import com.frame.template.service.demo.pojo.base.demoTree.DemoTreeUpdateInput;
import com.frame.template.service.demo.pojo.domain.DemoTree;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DemoTreeMapper extends BaseTreeMapper<DemoTree, DemoTreeDto, DemoTreeInsertInput, DemoTreeUpdateInput> {
}

