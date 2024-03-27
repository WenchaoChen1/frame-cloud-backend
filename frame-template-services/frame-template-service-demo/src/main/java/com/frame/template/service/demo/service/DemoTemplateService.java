// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.service;

import com.frame.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateFindAllByQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateInsertInput;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplatePageQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateUpdateInput;
import org.springframework.data.domain.Pageable;
import com.gstdev.cloud.commons.domain.Result;
import org.springframework.data.domain.Page;

import java.util.List;


public interface DemoTemplateService {

  Result<DemoTemplateDto> insert(DemoTemplateInsertInput insertInput);

  Result<DemoTemplateDto> update(DemoTemplateUpdateInput updateInput);

  Result<DemoTemplateDto> deleteById(String id);

  Page<DemoTemplateDto> page(DemoTemplatePageQueryCriteria queryCriteria, Pageable pageable);

  DemoTemplateDto findById(String id);

  List<DemoTemplateDto> findAllByQueryCriteria(DemoTemplateFindAllByQueryCriteria queryCriteria);

//////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////

}

