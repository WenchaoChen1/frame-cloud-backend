// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper.vo;

import com.frame.template.service.system.pojo.base.SysAttribute.SysAttributeVo;
import com.frame.template.service.system.pojo.base.account.AccountDto;
import com.frame.template.service.system.pojo.base.account.AccountVo;
import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
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
//public interface SysAttributeVoMapper extends BaseVoMapper<AccountVo, AccountDto> {
public interface SysAttributeVoMapper {
    SysAttributeVo entityToVo(SysAttribute entity);

    List<SysAttributeVo> entityToVo(List<SysAttribute> entity);

    default Page<SysAttributeVo> entityToVo(Page<SysAttribute> page) {
        List<SysAttributeVo> responses = this.entityToVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }
}

