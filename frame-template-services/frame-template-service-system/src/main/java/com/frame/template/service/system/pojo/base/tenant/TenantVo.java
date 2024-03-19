// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.tenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.template.common.base.baseTree.BaseTreeVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class TenantVo extends BaseTreeVo{
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updatedAt;
  private String updatedBy;
  private String tenantName;
  private String description;
  private Integer status;
  private String parentId;
  private String tenantCode;
  private Integer type;

  //-----------------自定义-----------
//  private String website;
//  private String addressLine1;
//  private String addressLine2;
//  private String city;
//  private String state;
//  private String country;
//  private String zipCode;
//  private String firstName;
//  private String lastName;
//  private String emailAddress;
//  private String phoneNumber;
//  private String logo;
//  private Integer users;
}

