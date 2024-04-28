package com.frame.template.service.system.service;

import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.dict.*;
import com.frame.template.service.system.pojo.entity.Dict;
import com.frame.template.service.system.mapper.DictMapper;
import com.frame.template.service.system.repository.DictRepository;
import com.gstdev.cloud.data.core.service.BaseTreeServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * @author zhucy
 */
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
    @Resource
    private RedisCurrentLoginInformation redisCurrentLoginInformation;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
        super(dictRepository, dictMapper);
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
        this.redisCurrentLoginInformation = redisCurrentLoginInformation;
    }

}
