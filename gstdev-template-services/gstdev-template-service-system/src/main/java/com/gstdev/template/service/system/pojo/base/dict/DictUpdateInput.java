// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.dict;

import com.gstdev.template.common.base.baseTree.BaseTreeUpdateInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictUpdateInput extends BaseTreeUpdateInput {

  private String id;
  private String code;
  private String description;
  private String name;
  private String parentId;
  private Integer sort;
  private Integer status;


}

