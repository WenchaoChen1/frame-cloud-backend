package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.gstdev.cloud.data.core.service.BaseService;

import java.util.List;

public interface SysAttributeService extends BaseService<SysAttribute, String> {

    SysAttribute assign(String attributeId, String[] permissionIds);

    List<SysAttribute> findAllByServiceId(String serviceId);

    List<SysAttribute> findByAttributeIdIn(List<String> ids);
}
