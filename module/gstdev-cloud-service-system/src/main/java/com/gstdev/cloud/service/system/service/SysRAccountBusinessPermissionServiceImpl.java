package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysRAccountBusinessPermission;
import com.gstdev.cloud.service.system.domain.generator.SysRAccountBusinessPermissionEmbeddablePK;
import com.gstdev.cloud.service.system.repository.SysRAccountBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class SysRAccountBusinessPermissionServiceImpl extends BaseServiceImpl<SysRAccountBusinessPermission, SysRAccountBusinessPermissionEmbeddablePK, SysRAccountBusinessPermissionRepository> implements SysRAccountBusinessPermissionService {

    @Resource
    private SysRAccountBusinessPermissionRepository repository;
    @Resource
    @Lazy
    private SysRAccountBusinessPermissionServiceImpl service;

    public SysRAccountBusinessPermissionServiceImpl(SysRAccountBusinessPermissionRepository sysRAccountBusinessPermissionRepository) {
        super(sysRAccountBusinessPermissionRepository);
    }

    @Override
    public SysRAccountBusinessPermissionRepository getRepository() {
        return repository;
    }

    @Override
    public SysRAccountBusinessPermissionServiceImpl getService() {
        return service;
    }


    @Override
    @Transactional
    public void updateAccountAssignedBusinessPermission(String roleIds, List<String> businessPermissionIds) {
        getRepository().deleteAllByAccountId(roleIds);
        if (businessPermissionIds.isEmpty()) {
            return;
        }
        saveAllAndFlush(toEntityList(roleIds, businessPermissionIds));
    }

    @Override
    public List<String> getAllBusinessPermissionIdByAccountId(String accountId) {
        return getRepository().findAllByAccountId(accountId).stream().map(SysRAccountBusinessPermission::getBusinessPermissionId).toList();
    }

    List<SysRAccountBusinessPermission> toEntityList(String roleId, List<String> businessPermissionIds) {
        return businessPermissionIds.stream().map(businessPermissionId -> {
            SysRAccountBusinessPermission sysRAccountBusinessPermission = new SysRAccountBusinessPermission();
            sysRAccountBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRAccountBusinessPermission.setAccountId(roleId);
            return sysRAccountBusinessPermission;
        }).toList();
    }

    List<SysRAccountBusinessPermission> toEntityList(List<String> roleIds, String businessPermissionId) {
        return roleIds.stream().map(roleId -> {
            SysRAccountBusinessPermission sysRAccountBusinessPermission = new SysRAccountBusinessPermission();
            sysRAccountBusinessPermission.setBusinessPermissionId(businessPermissionId);
            sysRAccountBusinessPermission.setAccountId(roleId);
            return sysRAccountBusinessPermission;
        }).toList();
    }



}
