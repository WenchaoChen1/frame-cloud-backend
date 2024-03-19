// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper;

import com.frame.template.common.base.baseTree.BaseTreeMapper;

import com.frame.template.service.system.pojo.base.tenant.TenantDto;
import com.frame.template.service.system.pojo.base.tenant.TenantInsertInput;
import com.frame.template.service.system.pojo.base.tenant.TenantUpdateInput;
import com.frame.template.service.system.pojo.domain.Tenant;
import com.frame.template.service.system.pojo.vo.tenant.TenantLoginInferiorUpdateInput;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface TenantMapper extends BaseTreeMapper<Tenant, TenantDto, TenantInsertInput, TenantUpdateInput> {
  Tenant toEntitySave(TenantInsertInput tenantInsertInput);

  TenantDto toDto(Tenant tenant);

  List<TenantDto> toDtos(List<Tenant> tenants);

    void copyModify(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput, @MappingTarget Tenant byId);
}

