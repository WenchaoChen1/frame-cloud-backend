// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.demo.pojo.base.demoTree;

import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DemoTreeUpdateInput extends BaseTreeUpdateInput {

  private String id;
  private Date createdAt;
  private String createdBy;
  private Date updatedAt;
  private String updatedBy;
  private String code;
  private String name;
  private String parentId;

}

