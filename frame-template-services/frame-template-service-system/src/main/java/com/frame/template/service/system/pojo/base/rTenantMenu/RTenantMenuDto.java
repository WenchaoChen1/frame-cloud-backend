// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.rTenantMenu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.template.service.system.pojo.base.menu.MenuDto;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class RTenantMenuDto extends BaseDto {

  private String id;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
  private Integer checked;
  private Integer status;
  private String tenantId;
  private String menuId;
  private MenuDto menuDto;


  public String getMenuId() {
    return menuDto.getId();
  }
}

