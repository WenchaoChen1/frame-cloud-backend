// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.menu;

import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuInsertInput extends BaseTreeInsertInput {

  private String code;
  private String description;
  private Integer hidden;
  private String icon;
  private String name;
  private String parentId;
  private String path;
  private String permission;
  private Integer sort;
  private Integer status;
  private Integer tenantEnable;
  private Integer type;
  private String url;
  private String location;

}

