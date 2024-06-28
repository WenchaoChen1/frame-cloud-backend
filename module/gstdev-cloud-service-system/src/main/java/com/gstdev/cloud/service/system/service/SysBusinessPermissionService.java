package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysBusinessPermission;
import com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission.UpdateBusinessPermissionAssignedTenantMenuIO;

import java.util.Set;

public interface SysBusinessPermissionService extends BaseService<SysBusinessPermission, String> {

    Set<String> getAllMenuIdByBusinessPermissionId(String businessPermissionId);

    Result<String> updateBusinessPermissionAssignedTenantMenu(UpdateBusinessPermissionAssignedTenantMenuIO updateBusinessPermissionAssignedTenantMenu);
}


