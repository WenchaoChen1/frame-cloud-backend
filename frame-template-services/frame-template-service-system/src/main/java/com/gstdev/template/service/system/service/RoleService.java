package com.gstdev.template.service.system.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.baseTree.BaseTreeService;
import com.gstdev.template.service.system.pojo.base.role.*;
import com.gstdev.template.service.system.pojo.domain.Role;

import java.util.List;

public interface RoleService extends BaseTreeService<RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {
  Result<List<String>> getAllByRoleId(String roleId);

  Result<String> insertRoleMenu(RoleInsertInput roleInsertInput);

  Role insert(Role role);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


