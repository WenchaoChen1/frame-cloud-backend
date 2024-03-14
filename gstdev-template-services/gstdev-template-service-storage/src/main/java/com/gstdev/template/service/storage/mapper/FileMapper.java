// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.storage.mapper;


import com.gstdev.template.common.base.BaseMapper;
import com.gstdev.template.service.storage.domain.entity.File;
import com.gstdev.template.service.storage.domain.base.FileDto;
import com.gstdev.template.service.storage.domain.base.FileInsertInput;
import com.gstdev.template.service.storage.domain.base.FileUpdateInput;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface FileMapper extends BaseMapper<File, FileDto, FileInsertInput, FileUpdateInput> {
}

