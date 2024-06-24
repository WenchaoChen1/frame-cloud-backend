// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.mapper;

import com.frame.template.service.demo.pojo.domain.DemoTemplate;
import com.frame.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateInsertInput;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateUpdateInput;
import com.gstdev.cloud.data.core.mapper.AbstractMapper;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DemoTemplateMapper extends AbstractMapper<DemoTemplateDto, DemoTemplate> {

    DemoTemplate toEntityInsert(DemoTemplateInsertInput demoTemplateInsertInput);

    void copyModify(DemoTemplateUpdateInput demoTemplateUpdateInput, @MappingTarget DemoTemplate depart);
}

