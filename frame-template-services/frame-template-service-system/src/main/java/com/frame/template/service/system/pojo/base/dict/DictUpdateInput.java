// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.dict;

import com.gstdev.cloud.data.core.pojo.BaseTreeUpdateInput;
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

