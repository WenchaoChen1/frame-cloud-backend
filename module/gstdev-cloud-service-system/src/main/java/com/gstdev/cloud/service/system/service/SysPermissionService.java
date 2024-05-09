package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseDtoService;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;

public interface SysPermissionService extends BaseDtoService<SysPermission, String, SysPermissionDto> {

    void permissionInit();
}


