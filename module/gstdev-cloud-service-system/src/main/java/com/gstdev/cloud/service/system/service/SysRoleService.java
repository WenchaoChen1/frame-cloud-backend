package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.domain.base.role.RoleDto;
import com.gstdev.cloud.service.system.domain.entity.SysRole;

import java.util.List;

public interface SysRoleService extends BaseTreeService<SysRole, String, RoleDto> {
    Result<List<String>> getAllTenantByRoleId(String roleId);

    Result<String> insertRoleMenu(String roleId, List<String> menuIds);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


