package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.base.dict.*;
import com.gstdev.cloud.service.system.pojo.entity.Dict;
import com.gstdev.cloud.service.system.mapper.DictMapper;
import com.gstdev.cloud.service.system.repository.DictRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;


//@Service
//@Transactional(rollbackFor = Exception.class)
//@RequiredArgsConstructor
//public class DictServiceImpl implements DictService {
//  private final DictMapper dictMapper;
//  private final DictRepository dictRepository;

@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends BaseTreeServiceImpl<Dict, String, DictRepository, DictMapper, DictDto, DictInsertInput, DictUpdateInput, DictPageQueryCriteria, DictFindAllByQueryCriteria> implements DictService {

    @Resource
    private DictRepository dictRepository;
    @Resource
    private DictMapper dictMapper;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        super(dictRepository, dictMapper);
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
    }

}