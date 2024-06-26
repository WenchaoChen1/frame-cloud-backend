package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;

public interface SysUserService extends BaseService<SysUser, String> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

    DefaultSecurityUser signInFindByUsername(String username);

    SysUser insertUserManageInitialization(InsertUserManageInitializationIO userInsertInput);
}


