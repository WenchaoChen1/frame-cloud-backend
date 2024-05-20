package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;
import com.gstdev.cloud.service.system.pojo.base.role.*;

import java.util.List;

public interface RoleService extends BaseTreeService<SysRole, String, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {
    Result<List<String>> getAllByRoleId(String roleId);

    Result<String> insertRoleMenu(RoleInsertInput roleInsertInput);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


