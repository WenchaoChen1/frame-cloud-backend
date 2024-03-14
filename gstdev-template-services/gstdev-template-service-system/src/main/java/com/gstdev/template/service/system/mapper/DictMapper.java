// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.mapper;

import com.gstdev.template.common.base.baseTree.BaseTreeMapper;
import com.gstdev.template.service.system.pojo.domain.Dict;
import com.gstdev.template.service.system.pojo.base.dict.DictDto;
import com.gstdev.template.service.system.pojo.base.dict.DictInsertInput;
import com.gstdev.template.service.system.pojo.base.dict.DictUpdateInput;
import com.gstdev.template.service.system.pojo.vo.menu.DictModifyInput;
import com.gstdev.template.service.system.pojo.vo.menu.DictSaveInput;
import org.mapstruct.*;

import java.util.List;

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

