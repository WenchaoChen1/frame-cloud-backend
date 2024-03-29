package com.frame.template.service.system.service;

import com.frame.template.common.base.baseTree.BaseTreeServiceImpl;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.system.mapper.DepartMapper;
import com.frame.template.service.system.pojo.base.depart.*;
import com.frame.template.service.system.pojo.domain.Depart;
import com.frame.template.service.system.repository.DepartRepository;
import com.frame.template.service.system.pojo.base.depart.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * @author zhucy
 */
//@Service
//@Transactional(rollbackFor = Exception.class)
//@RequiredArgsConstructor
//public class DepartServiceImpl implements DepartService {
//  private final DepartRepository departRepository;
//  private final DepartMapper departMapper;
@Service
@Transactional(readOnly = true)
public class DepartServiceImpl extends BaseTreeServiceImpl<DepartRepository, DepartMapper, Depart, DepartDto, DepartInsertInput, DepartUpdateInput, DepartPageQueryCriteria, DepartFindAllByQueryCriteria, RedisCurrentLoginInformation> implements DepartService {

  @Resource
  private DepartRepository departRepository;
  @Resource
  private DepartMapper departMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;

  public DepartServiceImpl(DepartRepository departRepository, DepartMapper departMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(departRepository, departMapper, redisCurrentLoginInformation);
    this.departRepository = departRepository;
    this.departMapper = departMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

}
