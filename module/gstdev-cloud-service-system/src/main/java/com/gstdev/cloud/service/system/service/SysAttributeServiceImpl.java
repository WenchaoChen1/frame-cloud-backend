package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.repository.SysAttributeRepository;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.repository.SysAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttribute, String, SysAttributeRepository> implements SysAttributeService {

//    private  SysPermissionRepository sysAttributeRepository;

    public SysAttributeServiceImpl(SysAttributeRepository sysAttributeRepository) {
        super(sysAttributeRepository);
    }


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

    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return getRepository().findAllByServiceId(serviceId);
    }

    public List<SysAttribute> findByAttributeIdIn(List<String> ids) {
        return getRepository().findByAttributeIdIn(ids);
    }
}
