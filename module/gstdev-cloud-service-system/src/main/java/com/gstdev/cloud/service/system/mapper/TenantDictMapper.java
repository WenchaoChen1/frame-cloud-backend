// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictDto;
import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictSaveInput;
import com.gstdev.cloud.data.core.mapper.AbstractMapper;
import com.gstdev.cloud.service.system.pojo.entity.TenantDict;
import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictDto;
import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
import com.gstdev.cloud.service.system.pojo.vo.TenantDict.TenantDictSaveInput;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface TenantDictMapper extends AbstractMapper<TenantDictDto, TenantDict> {
    /**
     * 转换
     *
     * @param tenantDictSaveInput
     * @return 数据库实体类
     */
    TenantDict toEntitySave(TenantDictSaveInput tenantDictSaveInput);

    /**
     * 转换
     *
     * @param tenantDictModifyInput
     * @return 数据库实体类
     */
    TenantDict toEntityModify(TenantDictModifyInput tenantDictModifyInput);

    /**
     * 转换
     *
     * @param tenantDictModifyInput
     * @param tenantDict
     */
    void copyModify(TenantDictModifyInput tenantDictModifyInput, @MappingTarget TenantDict tenantDict);

    /**
     * 转换
     *
     * @param tenantDict
     * @return
     */
    TenantDictDto toDto(TenantDict tenantDict);

    /**
     * 转换
     *
     * @param tenantDicts
     * @return
     */
    List<TenantDictDto> toDto(List<TenantDict> tenantDicts);

}

