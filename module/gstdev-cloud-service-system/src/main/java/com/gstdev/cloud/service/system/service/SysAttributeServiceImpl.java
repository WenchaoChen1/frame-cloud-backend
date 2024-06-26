package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;
import com.gstdev.cloud.service.system.repository.SysAttributeRepository;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttribute, String, SysAttributeRepository> implements SysAttributeService {

    @Resource
    private SysAttributeRepository sysAttributeRepository;

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public SysAttributeServiceImpl(SysAttributeRepository sysAttributeRepository) {
        super(sysAttributeRepository);
    }

    @Override
    public SysAttributeRepository getRepository() {
        return sysAttributeRepository;
    }


    @Override
    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return getRepository().findAllByServiceId(serviceId);
    }


    @Override
    public void updateAttributeManageAssignedPermission(AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO) {
        SysAttribute sysAttribute = findById(attributeManageAssignedPermissionIO.getAttributeId());
        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : attributeManageAssignedPermissionIO.getPermissionIds()) {
            Optional<SysPermission> sysPermission = sysPermissionRepository.findById(permissionId);
            sysPermission.ifPresent(sysPermissions::add);
        }
        sysAttribute.setPermissions(sysPermissions);
        saveAndFlush(sysAttribute);
    }

    @Override
    public Set<String> getAttributePermissionIdByAttributeId(String id) {
        return findById(id).getPermissions().stream().map(SysPermission::getPermissionId).collect(Collectors.toSet());
    }
}
