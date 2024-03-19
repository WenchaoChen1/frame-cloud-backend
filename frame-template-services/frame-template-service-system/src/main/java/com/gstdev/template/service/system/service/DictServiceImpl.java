package com.gstdev.template.service.system.service;

import com.gstdev.template.common.base.baseTree.BaseTreeServiceImpl;
import com.gstdev.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.gstdev.template.service.system.pojo.base.dict.*;
import com.gstdev.template.service.system.pojo.domain.Dict;
import com.gstdev.template.service.system.mapper.DictMapper;
import com.gstdev.template.service.system.repository.DictRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class DictServiceImpl extends BaseTreeServiceImpl<DictRepository, DictMapper, Dict, DictDto, DictInsertInput, DictUpdateInput, DictPageQueryCriteria, DictFindAllByQueryCriteria, RedisCurrentLoginInformation> implements DictService {

  @Resource
  private DictRepository dictRepository;
  @Resource
  private DictMapper dictMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;

  public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(dictRepository, dictMapper, redisCurrentLoginInformation);
    this.dictRepository = dictRepository;
    this.dictMapper = dictMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

}
