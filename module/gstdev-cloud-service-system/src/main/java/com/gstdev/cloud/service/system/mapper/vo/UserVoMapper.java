// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserLoginInferiorInsertInput;
import com.gstdev.cloud.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.user.UserVo;
import com.gstdev.cloud.service.system.pojo.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.user.UserUpdateInput;
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserVoMapper extends BaseVoMapper<UserVo, UserDto> {

    UserInsertInput toUserInsertInput(UserLoginInferiorInsertInput userLoginInferiorInsertInput);

    UserUpdateInput toUserUpdateInput(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput);

}

