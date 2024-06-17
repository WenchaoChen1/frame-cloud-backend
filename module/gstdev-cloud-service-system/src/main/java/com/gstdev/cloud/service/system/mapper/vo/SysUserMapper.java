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
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.base.user.UserVo;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UpdateUserManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.domain.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.domain.vo.user.UserUpdateInput;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysUserMapper extends BasePOJOMapper<SysUser, UserDto, UserVo, UserInsertInput, UserUpdateInput> {

    //    UserInsertInput toUserInsertInput(UserLoginInferiorInsertInput userLoginInferiorInsertInput);
//
//    UserUpdateInput toUserUpdateInput(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput);
//    SysUser toEntity(insertAndUpdateUserManageIO insertUserManageInput);
    SysUser toEntity(InsertUserManageIO insertUserManageInput);
    SysUser toEntity(InsertUserManageInitializationIO insertUserManageInitializationIO);

    void copy(UpdateUserManageIO updateUserManageIO, @MappingTarget SysUser sysUser);

    List<AccountListDto> accountListToDto(List<SysAccount> accountList);
}

