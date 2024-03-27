package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.role.*;
import com.frame.template.service.system.pojo.domain.Role;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.baseTree.BaseTreeService;
import com.frame.template.service.system.pojo.base.role.*;

import java.util.List;

public interface RoleService extends BaseTreeService<RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> {
  Result<List<String>> getAllByRoleId(String roleId);

  Result<String> insertRoleMenu(RoleInsertInput roleInsertInput);

  Role insert(Role role);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


