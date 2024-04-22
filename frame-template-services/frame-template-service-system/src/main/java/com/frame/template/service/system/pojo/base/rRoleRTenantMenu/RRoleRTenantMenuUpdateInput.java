// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rRoleRTenantMenu;

import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Deprecated
@Getter
@Setter
public class RRoleRTenantMenuUpdateInput extends BaseUpdateInput {

  private String id;
  private Date createdAt;
  private String createdBy;
  private Date updatedAt;
  private String updatedBy;
  private Integer checked;
  private String tenantId;
  private String menuId;
  private String roleId;


}

