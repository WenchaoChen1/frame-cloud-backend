// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.mapper.vo;

import com.frame.template.service.system.pojo.base.user.UserDto;
import com.frame.template.service.system.pojo.base.user.UserLoginInferiorInsertInput;
import com.frame.template.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.frame.template.service.system.pojo.base.user.UserVo;
import com.frame.template.service.system.pojo.vo.UserInsertInput;
import com.frame.template.service.system.pojo.vo.UserUpdateInput;
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

