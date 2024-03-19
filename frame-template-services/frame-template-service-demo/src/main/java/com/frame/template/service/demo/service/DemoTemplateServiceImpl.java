// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.service;


import cn.hutool.core.util.ObjectUtil;
import com.frame.template.service.demo.mapper.DemoTemplateMapper;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateFindAllByQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateInsertInput;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplatePageQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateUpdateInput;
import com.gstdev.cloud.commons.web.Result;
import com.gstdev.cloud.data.jpa.utils.QueryUtils;
import com.gstdev.cloud.web.exception.BadRequestException;
import com.frame.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.frame.template.service.demo.repository.DemoTemplateRepository;
import com.frame.template.service.demo.pojo.domain.DemoTemplate;
import com.frame.template.service.demo.pojo.vo.demoTemplate.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class DemoTemplateServiceImpl implements DemoTemplateService {

  @Resource
  private DemoTemplateRepository demoTemplateRepository;
  @Resource
  private DemoTemplateMapper demoTemplateMapper;
  @Resource
  private RedisCurrentLoginInformation redisCurrentLoginInformation;


  @Override
  @Transactional
  public Result<DemoTemplateDto> insert(DemoTemplateInsertInput demoTemplateInsertInput) {
    DemoTemplate demoTemplate = demoTemplateMapper.toEntityInsert(demoTemplateInsertInput);
    return Result.success(demoTemplateMapper.toDto(demoTemplateRepository.save(demoTemplate)));
  }


  @Override
  @Transactional
  public Result<DemoTemplateDto> update(DemoTemplateUpdateInput demoTemplateUpdateInput) {
    DemoTemplate demoTemplate = demoTemplateRepository.findById(demoTemplateUpdateInput.getId()).orElseGet(DemoTemplate::new);
    if (ObjectUtil.isEmpty(demoTemplate.getId())) {
      throw new BadRequestException("数据异常");
    }
    demoTemplateMapper.copyModify(demoTemplateUpdateInput, demoTemplate);
    return Result.success(demoTemplateMapper.toDto(demoTemplateRepository.save(demoTemplate)));
  }

  @Override
  @Transactional
  public Result<DemoTemplateDto> deleteById(String id) {
    demoTemplateRepository.deleteById(id);
    return Result.success();
  }

  @Override
  public Page<DemoTemplateDto> page(DemoTemplatePageQueryCriteria queryCriteria, Pageable pageable) {
    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "updatedAt"));
    Page<DemoTemplate> page = demoTemplateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder), pageable);
    return demoTemplateMapper.toDto(page);
  }

  @Override
  public DemoTemplateDto findById(String id) {
    return demoTemplateMapper.toDto(demoTemplateRepository.findById(id).orElseGet(DemoTemplate::new));
  }

  @Override
  public List<DemoTemplateDto> findAllByQueryCriteria(DemoTemplateFindAllByQueryCriteria queryCriteria) {
    return demoTemplateMapper.toDto(demoTemplateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, queryCriteria, criteriaBuilder)));
  }

////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////


}

