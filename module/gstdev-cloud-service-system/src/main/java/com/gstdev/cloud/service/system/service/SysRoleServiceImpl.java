package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.service.system.domain.base.role.RoleDto;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysRole;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysRole.InsertRoleMenuIO;
import com.gstdev.cloud.service.system.mapper.SysRoleMapper;
import com.gstdev.cloud.service.system.repository.SysRoleRepository;
import com.gstdev.cloud.service.system.repository.SysTenantMenuRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String, SysRoleRepository> implements SysRoleService {

    @Resource
    private SysTenantMenuRepository rTenantMenuRepository;
    @Resource
    private SysRoleRepository roleRepository;
    @Resource
    private SysRoleMapper roleMapper;

    public SysRoleServiceImpl(SysRoleRepository roleRepository, SysRoleMapper roleMapper) {
        super(roleRepository);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public SysRoleRepository getRepository() {
        return roleRepository;
    }

    @Override
    public Result<List<String>> getAllMenuIdByRoleId(String roleId) {
        List<String> strings = findById(roleId).getTenantMenus().stream().map(SysTenantMenu::getMenu).map(SysMenu::getId).toList();
        return Result.success(strings);
    }

    @Override
    public List<SysRole> getAllByTenantId(String tenantId) {
        return getRepository().findAllByTenantId(tenantId);
    }

    @Transactional
    @Override
    public Result<String> insertRoleMenu(InsertRoleMenuIO insertRoleMenuIO) {
        SysRole role = findById(insertRoleMenuIO.getRoleId());

        role.setTenantMenus(rTenantMenuRepository.findAllByTenantIdAndMenuIdIn(role.getTenantId(), insertRoleMenuIO.getMenuIds()));
        update(role);
        return Result.success();
    }


    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
}


