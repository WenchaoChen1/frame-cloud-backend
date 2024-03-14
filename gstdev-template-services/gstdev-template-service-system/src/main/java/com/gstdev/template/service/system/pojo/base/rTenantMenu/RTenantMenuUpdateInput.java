// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.rTenantMenu;

import com.gstdev.template.common.base.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RTenantMenuUpdateInput extends BaseUpdateInput {

  private String id;
  private Integer checked;
  private Integer status;
  private String tenantId;
  private String menuId;


}

