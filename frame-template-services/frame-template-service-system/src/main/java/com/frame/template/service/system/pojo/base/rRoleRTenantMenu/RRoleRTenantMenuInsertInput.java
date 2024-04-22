// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rRoleRTenantMenu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuInsertInput implements Serializable {

  List<String> rTenantMenuIds;
  private String id;
  private Integer checked;
  private String tenantId;
  private String menuId;
  private String roleId;


}

