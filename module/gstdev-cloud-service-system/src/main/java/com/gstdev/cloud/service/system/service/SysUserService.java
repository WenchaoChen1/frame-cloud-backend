package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.vo.user.AccountListDto;
import com.gstdev.cloud.data.core.service.BasePOJOService;

import java.util.List;

public interface SysUserService extends BasePOJOService<SysUser, String, UserDto> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    UserDto insertUserManageInitializationToDto(InsertUserManageInitializationIO userInsertInput);

    List<AccountListDto> getByIdToAccount(String id);

    DefaultSecurityUser signInFindByUsername(String username);

    SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput);
}


