// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.service.system.pojo.base.SysAttribute.SysAttributeVo;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionVo;
import com.gstdev.cloud.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionVo;
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
public interface SysPermissionVoMapper {
    SysPermissionVo entityToVo(SysPermission entity);

    List<SysPermissionVo> entityToVo(List<SysPermission> entity);

    default Page<SysPermissionVo> entityToVo(Page<SysPermission> page) {
        List<SysPermissionVo> responses = this.entityToVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }
}

