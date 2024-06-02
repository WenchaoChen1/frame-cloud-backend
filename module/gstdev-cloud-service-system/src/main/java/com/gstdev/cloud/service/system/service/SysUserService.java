package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.data.core.service.BasePOJOService;

import java.util.List;

public interface SysUserService extends BasePOJOService<SysUser, String, UserDto> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    UserDto insertUserInitializationToDto(SysUser usert, UserInsertInput userInsertInput);

    List<AccountListDto> getByIdToAccount(String id);

    DefaultSecurityUser signInFindByUsername(String username);
}


