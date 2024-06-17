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
import com.gstdev.cloud.service.system.domain.base.dict.DictDto;
import com.gstdev.cloud.service.system.domain.entity.SysDict;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DictMapper extends BaseDtoMapper<SysDict, DictDto> {
//    /**
//     * 转换
//     *
//     * @param dictSaveInput
//     * @return 数据库实体类
//     */
//    SysDict toEntitySave(DictSaveInput dictSaveInput);
//
//    /**
//     * 转换
//     *
//     * @param dictModifyInput
//     * @return 数据库实体类
//     */
//    SysDict toEntityModify(DictModifyInput dictModifyInput);
//
//    /**
//     * 转换
//     *
//     * @param dictModifyInput
//     * @param dict
//     */
//    void copyModify(DictModifyInput dictModifyInput, @MappingTarget SysDict dict);

    /**
     * 转换
     *
     * @param dict
     * @return
     */
    @Override
    DictDto toDto(SysDict dict);

    /**
     * 转换
     *
     * @param dicts
     * @return
     */
    @Override
    List<DictDto> toDto(List<SysDict> dicts);

}

