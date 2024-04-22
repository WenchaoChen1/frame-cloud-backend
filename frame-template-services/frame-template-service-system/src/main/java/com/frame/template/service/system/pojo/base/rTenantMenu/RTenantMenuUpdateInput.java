// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RTenantMenuUpdateInput extends BaseUpdateInput {

  private String id;
  private Integer checked;
  private Integer status;
  private String tenantId;
  private String menuId;


}

