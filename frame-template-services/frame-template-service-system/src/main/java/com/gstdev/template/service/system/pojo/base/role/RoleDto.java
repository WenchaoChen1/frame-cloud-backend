// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.template.common.base.baseTree.BaseTreeDto;
import com.gstdev.template.service.system.pojo.vo.RTenantMenu.RTenantMenuDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class RoleDto extends BaseTreeDto {

  private String id;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
  private String code;
  private String description;
  private String parentId;
  private String roleName;
  private Integer sort;
  private Integer status;
  private String tenantId;
  private List<RTenantMenuDto> rTenantMenuDtos;


}

