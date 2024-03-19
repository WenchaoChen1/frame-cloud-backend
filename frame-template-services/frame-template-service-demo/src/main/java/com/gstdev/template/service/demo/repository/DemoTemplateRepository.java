// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gstdev.template.service.demo.pojo.domain.DemoTemplate;

public interface DemoTemplateRepository extends JpaRepository<DemoTemplate, String>, JpaSpecificationExecutor<DemoTemplate> {


}

