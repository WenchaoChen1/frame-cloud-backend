// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.mapper;


import com.frame.template.service.storage.domain.base.FileDto;
import com.frame.template.service.storage.domain.base.FileInsertInput;
import com.frame.template.service.storage.domain.base.FileUpdateInput;
import com.frame.template.service.storage.domain.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface FileMapper extends BaseMapper<File, FileDto, FileInsertInput, FileUpdateInput> {
}

