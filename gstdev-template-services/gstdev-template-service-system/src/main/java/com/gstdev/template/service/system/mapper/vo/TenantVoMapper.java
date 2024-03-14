// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.mapper.vo;

import com.gstdev.template.common.base.baseTree.BaseTreeVoMapper;
import com.gstdev.template.service.system.pojo.base.tenant.TenantDto;
import com.gstdev.template.service.system.pojo.base.tenant.TenantInsertInput;
import com.gstdev.template.service.system.pojo.base.tenant.TenantUpdateInput;
import com.gstdev.template.service.system.pojo.base.tenant.TenantVo;
import com.gstdev.template.service.system.pojo.vo.tenant.TenantLoginInferiorInsertInput;
import com.gstdev.template.service.system.pojo.vo.tenant.TenantLoginInferiorUpdateInput;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface TenantVoMapper extends BaseTreeVoMapper<TenantVo, TenantDto> {
 TenantInsertInput toTenantInsertInput(TenantLoginInferiorInsertInput tenantLoginInferiorInsertInput);
 TenantUpdateInput toTenantInsertUpdate(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);


}

