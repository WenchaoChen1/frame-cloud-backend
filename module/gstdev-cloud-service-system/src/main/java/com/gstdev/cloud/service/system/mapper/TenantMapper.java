// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;


import com.gstdev.cloud.service.system.pojo.base.tenant.TenantDto;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantInsertInput;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.service.system.pojo.vo.tenant.TenantLoginInferiorUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantDto;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantInsertInput;
import com.gstdev.cloud.service.system.pojo.base.tenant.TenantUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.Tenant;
import com.gstdev.cloud.service.system.pojo.vo.tenant.TenantLoginInferiorUpdateInput;
import org.mapstruct.*;

import java.util.List;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface TenantMapper extends BaseTreeMapper<Tenant, TenantDto, TenantInsertInput, TenantUpdateInput> {
    Tenant toEntitySave(TenantInsertInput tenantInsertInput);

    TenantDto toDto(Tenant tenant);

    List<TenantDto> toDtos(List<Tenant> tenants);

    void copyModify(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput, @MappingTarget Tenant byId);
}

