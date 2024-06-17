package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseDtoService;
import com.gstdev.cloud.service.system.domain.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends BaseDtoService<SysPermission, String, SysPermissionDto> {

    void permissionInit();

    List<String> findDistinctPermissionTypes();
}


