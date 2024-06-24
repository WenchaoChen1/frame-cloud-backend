// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.service;

import com.frame.template.service.demo.pojo.dto.demoTemplate.DemoTemplateDto;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateFindAllByQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateInsertInput;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplatePageQueryCriteria;
import com.frame.template.service.demo.pojo.vo.demoTemplate.DemoTemplateUpdateInput;
import com.gstdev.cloud.base.definition.domain.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

