// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.mapper.vo;

import com.frame.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateVo;
import com.gstdev.cloud.commons.domain.Result;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DemoTemplateVoMapper {
  DemoTemplateVo toVo(DemoTemplateDto var1);

  List<DemoTemplateVo> toVo(List<DemoTemplateDto> var1);

  default Page<DemoTemplateVo> toVo(Page<DemoTemplateDto> page) {
    List<DemoTemplateVo> responses = this.toVo(page.getContent());
    return new PageImpl(responses, page.getPageable(), page.getTotalElements());
  }

  default Result<DemoTemplateVo> toVo(Result<DemoTemplateDto> result) {
    DemoTemplateDto data = result.getData();
    DemoTemplateVo v = toVo(data);
    Result<DemoTemplateVo> of = Result.success(result.getMessage(), result.getCode(), v);
    return of;
  }

  default Result<List<DemoTemplateVo>> toAllVo(Result<List<DemoTemplateDto>> result) {
    List<DemoTemplateDto> data = result.getData();
    List<DemoTemplateVo> v = toVo(data);
    Result<List<DemoTemplateVo>> of = Result.success(result.getMessage(), result.getCode(), v);
    return of;
  }

}

