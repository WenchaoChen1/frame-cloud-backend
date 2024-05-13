// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.autoconfigure.service.identity.mapper;

import com.frame.template.autoconfigure.service.identity.pojo.UserInput;
import com.frame.template.autoconfigure.service.identity.pojo.dto.UserDto;
import com.frame.template.autoconfigure.service.identity.pojo.entity.User;

import com.gstdev.cloud.data.core.mapper.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper extends AbstractMapper<UserDto, User> {
    User toEntity(UserInput userInput);
}
