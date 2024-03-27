package com.frame.template.service.demo.service;


import com.frame.template.service.demo.mapper.DemoMapper;
import com.frame.template.service.demo.pojo.base.demo.*;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.BaseServiceImpl;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.service.demo.pojo.base.demo.*;
import com.frame.template.service.demo.pojo.domain.Demo;
import com.frame.template.service.demo.pojo.dto.demo.DemoUpdateXXXXXXInput;
import com.frame.template.service.demo.repository.DemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.List;


/**
 * find开头的方法不允许写业务代码
 */
@Service
@Transactional(readOnly = true)
public class DemoServiceImpl extends BaseServiceImpl<DemoRepository, DemoMapper, Demo, DemoDto, DemoInsertInput, DemoUpdateInput, DemoPageQueryCriteria, DemoFindAllByQueryCriteria, RedisCurrentLoginInformation> implements DemoService {

  @Resource
  private DemoRepository demoRepository;

  @Resource
  private DemoMapper demoMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;

  public DemoServiceImpl(DemoRepository demoRepository, DemoMapper demoMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
    super(demoRepository, demoMapper, redisCurrentLoginInformation);
    this.demoRepository = demoRepository;
    this.demoMapper = demoMapper;
    this.redisCurrentLoginInformation = redisCurrentLoginInformation;
  }

  @Override
  public Result<Object> updatexxxxxx(DemoUpdateXXXXXXInput demoUpdateXXXXXXInput) {
    //TODO 这里就可以调取其他的的业务代码，save方法
    return null;
  }

  @Override
  public DemoDto findByName(String name) {
    return demoMapper.toDto(demoRepository.findByName(name));
  }

  @Override
  public DemoDto findByNameAndCode(String name, String code) {
    return demoMapper.toDto(demoRepository.findByNameAndCode(name, code));
  }

  @Override
  public List<DemoDto> findAllByName(String name) {
    return demoMapper.toDto(demoRepository.findAllByName(name));
  }

  @Override
  public DemoDto getCurrentLoginInformation(String name) {

    //TODO 这里可以写你的业务
    //TODO 调取其他的service
    //TODO
    Demo demo = new Demo();
    List<DemoDto> allByName = findAllByName("111");
    DemoDto aa = findByIdToDto("aa");

    return demoMapper.toDto(demo);
  }

}
