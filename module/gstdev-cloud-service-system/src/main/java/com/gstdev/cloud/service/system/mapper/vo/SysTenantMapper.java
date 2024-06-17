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
import com.gstdev.cloud.service.system.domain.base.tenant.TenantDto;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantInsertInput;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantUpdateInput;
import com.gstdev.cloud.service.system.domain.base.tenant.TenantVo;
import com.gstdev.cloud.service.system.domain.entity.SysTenant;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.InsertTenantManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.UpdateTenantManageIO;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysTenantMapper extends BaseTreeMapper<SysTenant, TenantDto, TenantVo, TenantInsertInput, TenantUpdateInput> {
    //    TenantInsertInput toTenantInsertInput(TenantLoginInferiorInsertInput tenantLoginInferiorInsertInput);
//
//    TenantUpdateInput toTenantInsertUpdate(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
//
    SysTenant toEntity(InsertTenantManageIO insertTenantManageIO);

    void copy(UpdateTenantManageIO updateTenantManageIO, @MappingTarget SysTenant sysTenant);

}

