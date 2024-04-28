// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper;


import com.frame.template.service.system.pojo.base.dict.DictDto;
import com.frame.template.service.system.pojo.base.dict.DictInsertInput;
import com.frame.template.service.system.pojo.base.dict.DictUpdateInput;
import com.frame.template.service.system.pojo.vo.menu.DictModifyInput;
import com.frame.template.service.system.pojo.vo.menu.DictSaveInput;
import com.frame.template.service.system.pojo.entity.Dict;
import org.mapstruct.*;

import java.util.List;

import com.gstdev.cloud.data.core.mapper.BaseTreeMapper;

/**
 * @author zhucy
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface DictMapper extends BaseTreeMapper<Dict, DictDto, DictInsertInput, DictUpdateInput> {
    /**
     * 转换
     *
     * @param dictSaveInput
     * @return 数据库实体类
     */
    Dict toEntitySave(DictSaveInput dictSaveInput);

    /**
     * 转换
     *
     * @param dictModifyInput
     * @return 数据库实体类
     */
    Dict toEntityModify(DictModifyInput dictModifyInput);

    /**
     * 转换
     *
     * @param dictModifyInput
     * @param dict
     */
    void copyModify(DictModifyInput dictModifyInput, @MappingTarget Dict dict);

    /**
     * 转换
     *
     * @param dict
     * @return
     */
    DictDto toDto(Dict dict);

    /**
     * 转换
     *
     * @param dicts
     * @return
     */
    List<DictDto> toDto(List<Dict> dicts);

}

