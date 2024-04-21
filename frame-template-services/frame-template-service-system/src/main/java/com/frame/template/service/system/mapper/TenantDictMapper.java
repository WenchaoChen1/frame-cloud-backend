// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper;

import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictDto;
import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictModifyInput;
import com.frame.template.service.system.pojo.vo.TenantDict.TenantDictSaveInput;
import com.gstdev.cloud.data.core.mapper.AbstractMapper;
import com.frame.template.service.system.pojo.domain.TenantDict;
import org.mapstruct.*;

import java.util.List;

/**
 * @author zhucy
 */
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

