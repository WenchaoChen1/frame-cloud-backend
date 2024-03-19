// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.mapper.vo;

import com.frame.template.common.base.BaseVoMapper;
import com.frame.template.service.system.pojo.base.user.UserDto;
import com.frame.template.service.system.pojo.base.user.UserLoginInferiorInsertInput;
import com.frame.template.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.frame.template.service.system.pojo.base.user.UserVo;
import com.frame.template.service.system.pojo.vo.UserInsertInput;
import com.frame.template.service.system.pojo.vo.UserUpdateInput;
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

