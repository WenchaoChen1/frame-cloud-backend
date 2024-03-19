// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.role;

import com.gstdev.template.common.base.baseTree.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleInsertInput extends BaseTreeInsertInput {

  private String id;
  private String code;
  private String description;
  private String parentId;
  private String roleName;
  private Integer sort;
  private Integer status;
  private String tenantId;
  List<String> menuIds;

}

