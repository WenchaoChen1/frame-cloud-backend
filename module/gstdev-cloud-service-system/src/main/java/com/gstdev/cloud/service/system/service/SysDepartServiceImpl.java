package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.mapper.DepartMapper;
import com.gstdev.cloud.service.system.domain.base.depart.*;
import com.gstdev.cloud.service.system.domain.entity.SysDepart;
import com.gstdev.cloud.service.system.repository.SysDepartRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SysDepartServiceImpl extends BaseTreeServiceImpl<SysDepart, String, SysDepartRepository, DepartMapper, DepartDto> implements SysDepartService {

    public SysDepartServiceImpl(SysDepartRepository departRepository, DepartMapper departMapper) {
        super(departRepository, departMapper);
    }

}
