package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.mapper.SysBusinessPermissionMapper;
import com.gstdev.cloud.service.system.repository.SysBusinessPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SysBusinessPermissionServiceImpl extends BaseServiceImpl<SysBusinessPermission, String, SysBusinessPermissionRepository> implements SysBusinessPermissionService {

    @Resource
    private SysBusinessPermissionRepository sysBusinessPermissionRepository;
    @Resource
    private SysBusinessPermissionMapper sysBusinessPermissionMapper;

    public SysBusinessPermissionServiceImpl(SysBusinessPermissionRepository sysBusinessPermissionRepository) {
        super(sysBusinessPermissionRepository);
    }


}
