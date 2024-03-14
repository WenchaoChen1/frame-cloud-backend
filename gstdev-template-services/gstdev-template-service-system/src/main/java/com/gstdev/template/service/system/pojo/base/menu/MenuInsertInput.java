// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.menu;

import com.gstdev.template.common.base.BaseInsertInput;
import com.gstdev.template.common.base.baseTree.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

