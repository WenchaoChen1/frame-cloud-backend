// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.identity.mapper;

import com.gstdev.cloud.data.jpa.mapper.AbstractMapper;
import com.gstdev.template.service.identity.contract.UserDto;
import com.gstdev.template.service.identity.contract.UserInput;
import com.gstdev.template.service.identity.domain.User;
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
