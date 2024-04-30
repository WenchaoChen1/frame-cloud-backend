package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.mapper.DepartMapper;
import com.gstdev.cloud.service.system.pojo.base.depart.*;
import com.gstdev.cloud.service.system.pojo.entity.Depart;
import com.gstdev.cloud.service.system.repository.DepartRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;


//@Service
//@Transactional(rollbackFor = Exception.class)
//@RequiredArgsConstructor
//public class DepartServiceImpl implements DepartService {
//  private final DepartRepository departRepository;
//  private final DepartMapper departMapper;
@Service
@Transactional(readOnly = true)
public class DepartServiceImpl extends BaseTreeServiceImpl<Depart, String, DepartRepository, DepartMapper, DepartDto, DepartInsertInput, DepartUpdateInput, DepartPageQueryCriteria, DepartFindAllByQueryCriteria> implements DepartService {

    @Resource
    private DepartRepository departRepository;
    @Resource
    private DepartMapper departMapper;

    public DepartServiceImpl(DepartRepository departRepository, DepartMapper departMapper) {
        super(departRepository, departMapper);
    }

}
