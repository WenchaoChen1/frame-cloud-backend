// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.demo.mapper;

import com.gstdev.cloud.data.jpa.mapper.AbstractMapper;
import com.gstdev.template.service.demo.pojo.domain.DemoTemplate;
import com.gstdev.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import com.gstdev.template.service.demo.pojo.vo.demoTemplate.DemoTemplateInsertInput;
import com.gstdev.template.service.demo.pojo.vo.demoTemplate.DemoTemplateUpdateInput;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DemoTemplateMapper extends AbstractMapper<DemoTemplateDto, DemoTemplate> {

  DemoTemplate toEntityInsert(DemoTemplateInsertInput demoTemplateInsertInput);

  void copyModify(DemoTemplateUpdateInput demoTemplateUpdateInput, @MappingTarget DemoTemplate depart);
}

