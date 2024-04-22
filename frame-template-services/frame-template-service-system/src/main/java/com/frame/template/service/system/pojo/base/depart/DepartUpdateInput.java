// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.depart;

import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartUpdateInput extends BaseTreeUpdateInput {

  private String id;
  private String code;
  private String description;
  private String name;
  private String parentId;
  private String shortName;
  private Integer sort;
  private Integer status;
  private String tenantId;


}

