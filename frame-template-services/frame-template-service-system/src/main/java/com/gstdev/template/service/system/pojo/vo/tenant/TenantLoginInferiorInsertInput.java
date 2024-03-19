// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.vo.tenant;

import com.gstdev.template.common.base.baseTree.BaseTreeInsertInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantLoginInferiorInsertInput extends BaseTreeInsertInput {
//  @ApiModelProperty(value = "parentId 不能为空", required = true)
//  @NotEmpty
  private String parentId;
  private String tenantCode;
  @ApiModelProperty(value = "companyName 不能为空", required = true)
  @NotEmpty
  private String tenantName;
  private String description;
  private Integer status;
  private Integer type;

  //-----------------自定义-----------

  private String website;
  @ApiModelProperty(value = "addressLine1 不能为空", required = true)
  @NotEmpty
  private String addressLine1;
  @ApiModelProperty(value = "addressLine2 不能为空")
  private String addressLine2;
  @ApiModelProperty(value = "city 不能为空", required = true)
  @NotEmpty
  private String city;
  @ApiModelProperty(value = "state 不能为空", required = true)
  @NotEmpty
  private String state;
  @ApiModelProperty(value = "country 不能为空", required = true)
  @NotEmpty
  private String country;
  @ApiModelProperty(value = "zipCode 不能为空", required = true)
  @NotEmpty
  private String zipCode;
  @ApiModelProperty(value = "firstName 不能为空", required = true)
  @NotEmpty
  private String firstName;
  @ApiModelProperty(value = "lastName 不能为空", required = true)
  @NotEmpty
  private String lastName;
  @ApiModelProperty(value = "emailAddress 不能为空", required = true)
  @NotEmpty
  private String emailAddress;
  @ApiModelProperty(value = "phoneNumber 不能为空", required = true)
  @NotEmpty
  private String phoneNumber;
  private String logo;
}

