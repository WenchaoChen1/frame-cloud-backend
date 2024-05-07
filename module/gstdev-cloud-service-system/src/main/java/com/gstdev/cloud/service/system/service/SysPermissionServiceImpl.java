package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.service.system.mapper.RoleMapper;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionServiceImpl extends BaseDtoServiceImpl<SysPermission, String, SysPermissionRepository, SysPermissionMapper, SysPermissionDto> implements SysPermissionService {

    //    private  SysPermissionRepository sysPermissionRepository;
//    @Resource
//    private SysPermissionMapper sysPermissionMapper;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        super(sysPermissionRepository, sysPermissionMapper);
    }


}
