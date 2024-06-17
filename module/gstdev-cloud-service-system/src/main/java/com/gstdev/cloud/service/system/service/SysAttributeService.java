package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.data.core.service.BaseService;

import java.util.List;

public interface SysAttributeService extends BaseService<SysAttribute, String> {

    SysAttribute assign(String attributeId, String[] permissionIds);

    List<SysAttribute> findAllByServiceId(String serviceId);

    List<SysAttribute> findByAttributeIdIn(List<String> ids);
}
