// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.tenant;

import com.frame.template.common.base.baseTree.BaseTreeInsertInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TenantInsertInput extends BaseTreeInsertInput {

  @ApiModelProperty(value = "parentId 不能为空", required = true)
  @NotEmpty
  private String parentId;
  private String tenantCode;
  private String tenantName;
  private String description;
  private Integer status;
  private Integer type;

  //-----------------自定义-----------

  private String companyName="";
  private String website="";
  private String addressLine1="";
  private String addressLine2="";
  private String city="";
  private String state="";
  private String country="";
  private String zipCode="";
  private String firstName="";
  private String lastName="";
  private String emailAddress="";
  private String phoneNumber="";
  private String logo="";
}

