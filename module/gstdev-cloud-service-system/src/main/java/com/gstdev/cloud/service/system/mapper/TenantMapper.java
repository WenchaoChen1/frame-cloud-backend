//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.gstdev.cloud.service.system.mapper;
//
//
//import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
//import com.gstdev.cloud.service.system.pojo.entity.SysTenant;
//import com.gstdev.cloud.service.system.pojo.base.tenant.TenantDto;
//import org.mapstruct.*;
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
//    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
//public interface TenantMapper extends BaseDtoMapper<SysTenant, TenantDto> {
////    Tenant toEntitySave(TenantInsertInput tenantInsertInput);
////
////    TenantDto toDto(Tenant tenant);
////
////    List<TenantDto> toDtos(List<Tenant> tenants);
////
////    void copyModify(TenantLoginInferiorUpdateInput tenantLoginInferiorUpdateInput, @MappingTarget Tenant byId);
//}
//
