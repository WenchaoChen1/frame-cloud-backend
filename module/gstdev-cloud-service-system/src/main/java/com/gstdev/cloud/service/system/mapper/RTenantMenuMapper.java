// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.service.system.domain.vo.RTenantMenu.RTenantMenuModifyInput;
import com.gstdev.cloud.service.system.domain.vo.RTenantMenu.RTenantMenuSaveInput;
import com.gstdev.cloud.service.system.domain.entity.RTenantMenu;
import com.gstdev.cloud.service.system.domain.base.rTenantMenu.RTenantMenuDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuMapper extends BaseDtoMapper<RTenantMenu, RTenantMenuDto> {
    @Override
    RTenantMenu toEntity(RTenantMenuDto tenantDto);

    void copyModify(RTenantMenuSaveInput tenantSaveInput, @MappingTarget RTenantMenu tenant);

    RTenantMenu toEntitySave(RTenantMenuSaveInput tenantSaveInput);

    List<RTenantMenu> toEntitySave(List<RTenantMenuSaveInput> tenantSaveInputs);

    RTenantMenu toEntityModify(RTenantMenuModifyInput tenantModifyInput);
    @Override
    RTenantMenuDto toDto(RTenantMenu tenant);

    List<RTenantMenuDto> toDtos(List<RTenantMenu> tenants);
}

