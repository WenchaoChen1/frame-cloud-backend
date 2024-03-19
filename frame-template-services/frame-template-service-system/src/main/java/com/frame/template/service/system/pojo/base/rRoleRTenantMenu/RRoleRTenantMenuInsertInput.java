// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rRoleRTenantMenu;

import com.frame.template.common.base.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuInsertInput extends BaseInsertInput {

  private String id;
  private Integer checked;
  private String tenantId;
  private String menuId;
  private String roleId;
  List<String> rTenantMenuIds;


}

