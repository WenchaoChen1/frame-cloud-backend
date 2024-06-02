package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.base.dict.*;
import com.gstdev.cloud.service.system.pojo.entity.Dict;
import com.gstdev.cloud.service.system.mapper.DictMapper;
import com.gstdev.cloud.service.system.repository.DictRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;


@Transactional(readOnly = true)
public class SysDictServiceImpl extends BaseTreeServiceImpl<Dict, String, DictRepository, DictMapper, DictDto> implements SysDictService {
    private static final Logger log = LoggerFactory.getLogger(SysDictServiceImpl.class);
    @Resource
    private DictRepository dictRepository;

    public SysDictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        super(dictRepository, dictMapper);
        this.dictRepository = dictRepository;
    }

    public DictRepository getRepository() {
        return dictRepository;
    }
}
