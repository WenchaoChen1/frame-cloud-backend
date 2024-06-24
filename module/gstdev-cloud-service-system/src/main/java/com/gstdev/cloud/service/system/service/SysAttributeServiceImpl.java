package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;
import com.gstdev.cloud.service.system.repository.SysAttributeRepository;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttribute, String, SysAttributeRepository> implements SysAttributeService {

    @Resource
    private SysAttributeRepository sysAttributeRepository;
    @Resource
    private SysPermissionService sysPermissionService;

    public SysAttributeServiceImpl(SysAttributeRepository sysAttributeRepository) {
        super(sysAttributeRepository);
    }

    @Override
    public SysAttributeRepository getRepository() {
        return sysAttributeRepository;
    }

    @Override
    public SysAttribute assign(String attributeId, String[] permissionIds) {

        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : permissionIds) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(permissionId);
            sysPermissions.add(sysPermission);
        }

        SysAttribute sysAttribute = findById(attributeId);
        sysAttribute.setPermissions(sysPermissions);

        return saveAndFlush(sysAttribute);
    }

    @Override
    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return getRepository().findAllByServiceId(serviceId);
    }

    @Override
    public List<SysAttribute> findByAttributeIdIn(List<String> ids) {
        return getRepository().findByAttributeIdIn(ids);
    }

    @Override
    public void attributeManageAssignedPermission(AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO) {
        SysAttribute sysAttribute = findById(attributeManageAssignedPermissionIO.getAttributeId());
        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : attributeManageAssignedPermissionIO.getPermissionId()) {
            SysPermission sysPermission = sysPermissionService.findById(permissionId);
            sysPermissions.add(sysPermission);
        }
        sysAttribute.setPermissions(sysPermissions);
        saveAndFlush(sysAttribute);
    }

    @Override
    public Set<String> getAttributePermissionIdByAttributeId(String id) {
        return findById(id).getPermissions().stream().map(SysPermission::getPermissionId).collect(Collectors.toSet());
    }
}
