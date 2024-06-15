// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserVo;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.o.sysUser.InsertAndUpdateUserManageIO;
import com.gstdev.cloud.service.system.pojo.o.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.pojo.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.user.UserUpdateInput;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserVoMapper extends BasePOJOMapper<SysUser, UserDto, UserVo, UserInsertInput, UserUpdateInput>{

//    UserInsertInput toUserInsertInput(UserLoginInferiorInsertInput userLoginInferiorInsertInput);
//
//    UserUpdateInput toUserUpdateInput(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput);
//    SysUser toEntity(insertAndUpdateUserManageIO insertUserManageInput);
    SysUser toEntity(InsertUserManageInitializationIO insertUserManageInput);
    void copy(InsertAndUpdateUserManageIO insertAndUpdateUserManageIO, @MappingTarget SysUser sysUser);
}

