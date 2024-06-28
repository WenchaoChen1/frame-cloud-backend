package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.entity.SysMenu;
import com.gstdev.cloud.service.system.domain.entity.SysTenantMenu;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.UpdateBusinessPermissionAssignedTenantMenuIO;
import com.gstdev.cloud.service.system.mapper.SysBusinessPermissionMapper;
import com.gstdev.cloud.service.system.repository.SysBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class SysBusinessPermissionServiceImpl extends BaseServiceImpl<SysBusinessPermission, String, SysBusinessPermissionRepository> implements SysBusinessPermissionService {

    @Resource
    private SysBusinessPermissionRepository sysBusinessPermissionRepository;
    @Resource
    private SysBusinessPermissionMapper sysBusinessPermissionMapper;
    @Resource
    @Lazy
    private SysRTenantMenuBusinessPermissionService sysRTenantMenuBusinessPermissionService;
    @Resource
    @Lazy
    private SysBusinessPermissionServiceImpl sysBusinessPermissionService;

    public SysBusinessPermissionServiceImpl(SysBusinessPermissionRepository sysBusinessPermissionRepository) {
        super(sysBusinessPermissionRepository);
    }


    @Override
    public Set<String> getAllMenuIdByBusinessPermissionId(String businessPermissionId) {
        SysBusinessPermission byId = sysBusinessPermissionService.findById(businessPermissionId);
        return byId.getSysTenantMenus().stream().map(SysTenantMenu::getMenu).map(SysMenu::getId).collect(Collectors.toSet());
    }

    @Override
    public Result<String> updateBusinessPermissionAssignedTenantMenu(UpdateBusinessPermissionAssignedTenantMenuIO var) {
        sysRTenantMenuBusinessPermissionService.updateBusinessPermissionAssignedTenantMenu(var.getBusinessPermissionId(), var.getTenantMenuIds());
        return null;
    }
}
