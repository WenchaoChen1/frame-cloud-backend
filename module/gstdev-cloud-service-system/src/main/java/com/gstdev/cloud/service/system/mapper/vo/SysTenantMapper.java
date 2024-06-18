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
import com.gstdev.cloud.service.system.domain.pojo.sysTenant.*;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysTenantMapper extends BaseTreeMapper<SysTenant, TenantDto, TenantVo, TenantInsertInput, TenantUpdateInput> {
    //    TenantInsertInput toTenantInsertInput(TenantLoginInferiorInsertInput tenantLoginInferiorInsertInput);
//
//    TenantUpdateInput toTenantInsertUpdate(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput);
//
    SysTenant toEntity(InsertTenantManageIO insertTenantManageIO);

    TenantManageDetailVo toTenantManageDetailVo(SysTenant sysTenant);
    List<TenantManageTreeVo> toTenantManageTreeVo(List<TenantDto> sysTenant);
    List<TenantManagePageVo> toTenantManagePageVo(List<SysTenant> sysTenant);
    default Page<TenantManagePageVo> toTenantManagePageVo(Page<SysTenant> page) {
        List<TenantManagePageVo> responses = this.toTenantManagePageVo(page.getContent());
        return new PageImpl(responses, page.getPageable(), page.getTotalElements());
    }


    void copy(UpdateTenantManageIO updateTenantManageIO, @MappingTarget SysTenant sysTenant);

}

