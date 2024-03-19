// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.pojo.vo.demoTemplate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

@Getter
@Setter
public class DemoTemplateUpdateInput {

  private String id;
  private Date createdAt;
  private String createdBy;
  private Date updatedAt;
  private String updatedBy;
  private String code;
  private String name;


}

