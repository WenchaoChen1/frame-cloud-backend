// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.demo.service;


import com.gstdev.template.common.base.baseTree.BaseTreeServiceImpl;
import com.gstdev.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;

import com.gstdev.template.service.demo.mapper.DemoTreeMapper;
import com.gstdev.template.service.demo.pojo.base.demoTree.*;
import com.gstdev.template.service.demo.pojo.domain.DemoTree;
import com.gstdev.template.service.demo.repository.DemoTreeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(readOnly = true)
public class DemoTreeServiceImpl extends BaseTreeServiceImpl<DemoTreeRepository,
  DemoTreeMapper,
  DemoTree,
  DemoTreeDto,
  DemoTreeInsertInput,
  DemoTreeUpdateInput,
  DemoTreePageQueryCriteria,
  DemoTreeFindAllByQueryCriteria,
  RedisCurrentLoginInformation
  >
  implements DemoTreeService {

  @Resource
  private DemoTreeRepository demoTreeRepository;
  @Resource
  private DemoTreeMapper demoTreeMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;

  public DemoTreeServiceImpl(DemoTreeRepository demoTreeRepository, DemoTreeMapper demoTreeMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(demoTreeRepository, demoTreeMapper, redisCurrentLoginInformation);
    this.demoTreeRepository = demoTreeRepository;
    this.demoTreeMapper = demoTreeMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }


    /*------------------------------------------以上是系统访问控制代码--------------------------------------------*/
}

