// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.repository;

import com.frame.template.service.demo.pojo.domain.DemoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoTemplateRepository extends JpaRepository<DemoTemplate, String>, JpaSpecificationExecutor<DemoTemplate> {


}

