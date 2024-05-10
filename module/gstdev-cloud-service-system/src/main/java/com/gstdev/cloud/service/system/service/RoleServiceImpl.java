package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.pojo.base.role.*;
import com.gstdev.cloud.service.system.pojo.entity.Menu;
import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import com.gstdev.cloud.service.system.mapper.RoleMapper;
import com.gstdev.cloud.service.system.repository.RTenantMenuRepository;
import com.gstdev.cloud.service.system.repository.RoleRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.List;


@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseTreeServiceImpl<SysRole, String, RoleRepository, RoleMapper, RoleDto, RoleInsertInput, RoleUpdateInput, RolePageQueryCriteria, RoleFindAllByQueryCriteria> implements RoleService {

    @Resource
    private RTenantMenuRepository rTenantMenuRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
    }

    @Override
    public Result<List<String>> getAllByRoleId(String roleId) {
        List<String> strings = findById(roleId).getRTenantMenus().stream().map(RTenantMenu::getMenu).map(Menu::getId).toList();
        return Result.success(strings);
    }

    @Override
    public Result<String> insertRoleMenu(RoleInsertInput roleInsertInput) {
        SysRole role = findById(roleInsertInput.getId());

        role.setRTenantMenus(rTenantMenuRepository.findAllByTenantIdAndMenuIdIn(role.getTenantId(), roleInsertInput.getMenuIds()));
        update(role);
        return Result.success();
    }


    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


