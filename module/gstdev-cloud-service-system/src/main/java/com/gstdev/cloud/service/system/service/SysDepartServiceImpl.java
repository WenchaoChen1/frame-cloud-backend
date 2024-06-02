package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.mapper.DepartMapper;
import com.gstdev.cloud.service.system.pojo.base.depart.*;
import com.gstdev.cloud.service.system.pojo.entity.Depart;
import com.gstdev.cloud.service.system.repository.DepartRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SysDepartServiceImpl extends BaseTreeServiceImpl<Depart, String, DepartRepository, DepartMapper, DepartDto> implements SysDepartService {

    public SysDepartServiceImpl(DepartRepository departRepository, DepartMapper departMapper) {
        super(departRepository, departMapper);
    }

}
