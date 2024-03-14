// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.mapper;

import com.gstdev.template.common.base.BaseMapper;
import com.gstdev.template.service.system.pojo.domain.RTenantMenu;
import com.gstdev.template.service.system.pojo.base.rTenantMenu.RTenantMenuDto;
import com.gstdev.template.service.system.pojo.base.rTenantMenu.RTenantMenuInsertInput;
import com.gstdev.template.service.system.pojo.base.rTenantMenu.RTenantMenuUpdateInput;
import com.gstdev.template.service.system.pojo.vo.RTenantMenu.RTenantMenuModifyInput;
import com.gstdev.template.service.system.pojo.vo.RTenantMenu.RTenantMenuSaveInput;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface RTenantMenuMapper extends BaseMapper<RTenantMenu, RTenantMenuDto, RTenantMenuInsertInput, RTenantMenuUpdateInput> {
  RTenantMenu toEntity(RTenantMenuDto tenantDto);

  void copyModify(RTenantMenuSaveInput tenantSaveInput, @MappingTarget RTenantMenu tenant);

  RTenantMenu toEntitySave(RTenantMenuSaveInput tenantSaveInput);

  List<RTenantMenu> toEntitySave(List<RTenantMenuSaveInput> tenantSaveInputs);

  RTenantMenu toEntityModify(RTenantMenuModifyInput tenantModifyInput);

  RTenantMenuDto toDto(RTenantMenu tenant);

  List<RTenantMenuDto> toDtos(List<RTenantMenu> tenants);
}

