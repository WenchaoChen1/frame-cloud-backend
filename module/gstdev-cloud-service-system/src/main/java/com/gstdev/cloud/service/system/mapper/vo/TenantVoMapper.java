// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantDto;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantInsertInput;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantVo;
import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.service.system.pojo.vo.tenant.TenantLoginInferiorInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.tenant.TenantLoginInferiorUpdateInput;
import com.gstdev.cloud.data.core.mapper.BaseTreeVoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface TenantVoMapper  extends BaseTreeMapper<Tenant, TenantDto, TenantVo, TenantInsertInput, TenantUpdateInput> {
//    TenantInsertInput toTenantInsertInput(TenantLoginInferiorInsertInput tenantLoginInferiorInsertInput);
//
//    TenantUpdateInput toTenantInsertUpdate(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
//

}

