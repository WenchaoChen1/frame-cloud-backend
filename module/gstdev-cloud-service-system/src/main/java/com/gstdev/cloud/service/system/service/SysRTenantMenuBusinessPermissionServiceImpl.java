package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRTenantMenuBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRTenantMenuBusinessPermissionEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRTenantMenuBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class SysRTenantMenuBusinessPermissionServiceImpl extends BaseServiceImpl<SysRTenantMenuBusinessPermission, SysRTenantMenuBusinessPermissionEmbeddablePK, SysRTenantMenuBusinessPermissionRepository> implements SysRTenantMenuBusinessPermissionService {

    @Resource
    private SysRTenantMenuBusinessPermissionRepository repository;
    @Resource
    @Lazy
    private SysRTenantMenuBusinessPermissionServiceImpl service;

    public SysRTenantMenuBusinessPermissionServiceImpl(SysRTenantMenuBusinessPermissionRepository sysRTenantMenuBusinessPermissionRepository) {
        super(sysRTenantMenuBusinessPermissionRepository);
    }

    @Override
    public SysRTenantMenuBusinessPermissionRepository getRepository() {
        return repository;
    }

    @Override
    public SysRTenantMenuBusinessPermissionServiceImpl getService() {
        return service;
    }

    @Transactional
    public void saveAndFlush(String businessPermissionId, List<String> tenantMenuIds) {
        if (tenantMenuIds == null || tenantMenuIds.isEmpty()) {
            return;
        }
        tenantMenuIds.forEach(tenantMenuId -> {
            SysRTenantMenuBusinessPermission sysRTenantMenuBusinessPermission = new SysRTenantMenuBusinessPermission();
            sysRTenantMenuBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRTenantMenuBusinessPermission.setTenantMenuId(tenantMenuId);
            saveAndFlush(sysRTenantMenuBusinessPermission);
        });
    }

    @Override
    @Transactional
    public void updateBusinessPermissionAssignedTenantMenu(String businessPermissionId, List<String> tenantMenuIds) {
        if (tenantMenuIds == null || tenantMenuIds.isEmpty()) {
            getRepository().deleteAllByBusinessPermissionId(businessPermissionId);
            return;
        }
        getRepository().findAllByBusinessPermissionId(businessPermissionId).forEach(sysRTenantMenuBusinessPermission -> {
            if (!tenantMenuIds.contains(sysRTenantMenuBusinessPermission.getTenantMenuId())) {
                delete(sysRTenantMenuBusinessPermission);
                tenantMenuIds.remove(sysRTenantMenuBusinessPermission.getTenantMenuId());
            }
        });
        saveAndFlush(businessPermissionId, tenantMenuIds);

    }
}
