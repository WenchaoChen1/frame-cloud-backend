package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.InsertRoleMenuIO;

import java.util.List;
import java.util.Set;

public interface SysRoleService extends BaseService<SysRole, String> {
    Result<List<String>> getAllMenuIdByRoleId(String roleId);

    List<SysRole> getAllByTenantId(String roleId);

    Result<String> insertRoleMenu(InsertRoleMenuIO insertRoleMenuIO);

    Set<String> getAllBusinessPermissionIdByRoleId(String roleId);


    void updateRoleAssignedBusinessPermission(String roleId, List<String> businessPermissionIds);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


