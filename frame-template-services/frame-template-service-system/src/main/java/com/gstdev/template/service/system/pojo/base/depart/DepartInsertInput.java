// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.depart;

import com.gstdev.template.common.base.baseTree.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartInsertInput extends BaseTreeInsertInput {

  private String code;
  private String description;
  private String name;
  private String parentId;
  private String shortName;
  private Integer sort;
  private Integer status;
  private String tenantId;


}

