// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.mapper;

import com.frame.template.common.base.BaseMapper;
import com.frame.template.service.demo.pojo.base.demo.DemoDto;
import com.frame.template.service.demo.pojo.base.demo.DemoInsertInput;
import com.frame.template.service.demo.pojo.base.demo.DemoUpdateInput;
import com.frame.template.service.demo.pojo.domain.Demo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DemoMapper extends BaseMapper<Demo, DemoDto, DemoInsertInput, DemoUpdateInput> {
//
//    Demo toEntitySave(DemoInsertInput demoInsertInput);
//
//    Demo toEntityModify(DemoUpdateInput demoUpdateInput);
//
//    void copyModify(DemoUpdateInput demoUpdateInput, @MappingTarget Demo depart);

  Demo toEntityaaaSave(DemoInsertInput demoInsertInput);

}

