package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.entity.SysPermission;
import com.frame.template.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String, SysPermissionRepository> implements SysPermissionService {

//    private  SysPermissionRepository sysPermissionRepository;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository) {
        super(sysPermissionRepository);
    }


}
