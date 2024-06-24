package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.pojo.sysAttribute.AttributeManageAssignedPermissionIO;

import java.util.List;
import java.util.Set;

public interface SysAttributeService extends BaseService<SysAttribute, String> {

    SysAttribute assign(String attributeId, String[] permissionIds);

    List<SysAttribute> findAllByServiceId(String serviceId);

    List<SysAttribute> findByAttributeIdIn(List<String> ids);

    void attributeManageAssignedPermission(AttributeManageAssignedPermissionIO attributeManageAssignedPermissionIO);

    Set<String> getAttributePermissionIdByAttributeId(String id);
}
