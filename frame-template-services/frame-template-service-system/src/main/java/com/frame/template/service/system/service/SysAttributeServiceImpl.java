package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.entity.SysAttribute;
import com.frame.template.service.system.pojo.entity.SysPermission;
import com.frame.template.service.system.repository.SysAttributeRepository;
import com.frame.template.service.system.repository.SysPermissionRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttribute, String, SysAttributeRepository> implements SysAttributeService {

//    private  SysPermissionRepository sysAttributeRepository;

    public SysAttributeServiceImpl(SysAttributeRepository sysAttributeRepository) {
        super(sysAttributeRepository);
    }


}
