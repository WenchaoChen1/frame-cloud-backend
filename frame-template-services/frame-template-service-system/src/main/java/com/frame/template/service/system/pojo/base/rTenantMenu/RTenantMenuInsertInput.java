// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rTenantMenu;

import com.frame.template.common.base.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RTenantMenuInsertInput extends BaseInsertInput {


  private Integer checked;
  private Integer status;
  private String tenantId;
  private String menuId;
  private List<String> menuIds;

}

