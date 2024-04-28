package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.role.*;
import com.frame.template.service.system.pojo.entity.Role;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseTreeService;

import java.util.List;

public interface RoleService extends BaseTreeService<Role, String, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {
    Result<List<String>> getAllByRoleId(String roleId);

    Result<String> insertRoleMenu(RoleInsertInput roleInsertInput);

    Role insert(Role role);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


