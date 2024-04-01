// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.template.common.base.BaseDto;
import com.frame.template.common.constant.AccountTypeConstants;
import com.frame.template.service.system.pojo.base.user.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class AccountDto extends BaseDto {

  private String id;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
  private Integer deleted;
  private String identity;
  private String tenantId;
  private String name;
  private String type;
  private UserDto user;

  public String getUserId() {
    return user.getId();
  }

  public AccountTypeConstants getAccountTypeConstants() {
    return AccountTypeConstants.getAccountTypeConstants(this.type);
  }
}

