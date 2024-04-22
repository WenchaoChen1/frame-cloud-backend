// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.role;

import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleInsertInput extends BaseTreeInsertInput {

  List<String> menuIds;
  private String id;
  private String code;
  private String description;
  private String parentId;
  private String roleName;
  private Integer sort;
  private Integer status;
  private String tenantId;

}

