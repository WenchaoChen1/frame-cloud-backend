// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RTenantMenuInsertInput extends BaseInsertInput implements Serializable {


  private Integer checked;
  private Integer status;
  private String tenantId;
  private String menuId;
  private List<String> menuIds;

}

