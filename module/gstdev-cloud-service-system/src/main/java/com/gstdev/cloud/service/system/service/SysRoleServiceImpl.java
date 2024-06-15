package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.pojo.base.role.*;
import com.gstdev.cloud.service.system.pojo.entity.SysMenu;
import com.gstdev.cloud.service.system.pojo.entity.RTenantMenu;
import com.gstdev.cloud.service.system.pojo.entity.SysRole;
import com.gstdev.cloud.service.system.mapper.RoleMapper;
import com.gstdev.cloud.service.system.repository.SysRTenantMenuRepository;
import com.gstdev.cloud.service.system.repository.SysRoleRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.List;


@Transactional(readOnly = true)
public class SysRoleServiceImpl extends BaseTreeServiceImpl<SysRole, String, SysRoleRepository, RoleMapper, RoleDto> implements SysRoleService {

    @Resource
    private SysRTenantMenuRepository rTenantMenuRepository;    @Resource
    private SysRoleRepository roleRepository;

    public SysRoleServiceImpl(SysRoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
        this.roleRepository=roleRepository;
    }
    public SysRoleRepository getRepository() {
        return roleRepository;
    }
    @Override
    public Result<List<String>> getAllByRoleId(String roleId) {
        List<String> strings = findById(roleId).getRTenantMenus().stream().map(RTenantMenu::getMenu).map(SysMenu::getId).toList();
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


