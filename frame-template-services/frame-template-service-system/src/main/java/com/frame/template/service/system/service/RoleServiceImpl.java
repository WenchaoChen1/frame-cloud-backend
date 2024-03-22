package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.role.*;
import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.base.baseTree.BaseTreeServiceImpl;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.role.*;
import com.frame.template.service.system.pojo.domain.Menu;
import com.frame.template.service.system.pojo.domain.RTenantMenu;
import com.frame.template.service.system.pojo.domain.Role;
import com.frame.template.service.system.mapper.RoleMapper;
import com.frame.template.service.system.repository.RTenantMenuRepository;
import com.frame.template.service.system.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseTreeServiceImpl<RoleRepository, RoleMapper, Role, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria, RedisCurrentLoginInformation> implements RoleService {

  @Resource
  private RoleRepository roleRepository;
  @Resource
  private RoleMapper roleMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;
  @Resource
  private RTenantMenuRepository rTenantMenuRepository;

  public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(roleRepository, roleMapper, redisCurrentLoginInformation);
    this.roleRepository = roleRepository;
    this.roleMapper = roleMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  @Override
  public Result<List<String>> getAllByRoleId(String roleId) {
    List<String> strings = findById(roleId).getRTenantMenus().stream().map(RTenantMenu::getMenu).map(Menu::getId).toList();
    return Result.success(strings);
  }

  @Override
  public Result<String> insertRoleMenu(RoleInsertInput roleInsertInput) {
    Role role = findById(roleInsertInput.getId());

    role.setRTenantMenus(rTenantMenuRepository.findAllByTenantIdAndMenuIdIn(role.getTenantId(), roleInsertInput.getMenuIds()));
    update(role);
    return Result.success();
  }


  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


