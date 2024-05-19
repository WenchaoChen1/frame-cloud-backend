package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.base.dict.*;
import com.gstdev.cloud.service.system.pojo.entity.Dict;
import com.gstdev.cloud.service.system.mapper.DictMapper;
import com.gstdev.cloud.service.system.repository.DictRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import com.gstdev.cloud.service.system.repository.MenuRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;


//@Service
//@Transactional(rollbackFor = Exception.class)
//@RequiredArgsConstructor
//public class DictServiceImpl implements DictService {
//  private final DictMapper dictMapper;
//  private final DictRepository dictRepository;

//@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends BaseTreeServiceImpl<Dict, String, DictRepository, DictMapper, DictDto, DictInsertInput, DictUpdateInput, DictPageQueryCriteria, DictFindAllByQueryCriteria> implements DictService {
    private static final Logger log = LoggerFactory.getLogger(DictServiceImpl.class);
    @Resource
    private DictRepository dictRepository;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        super(dictRepository, dictMapper);
        this.dictRepository = dictRepository;
    }

    public DictRepository getRepository() {
        return dictRepository;
    }
}
