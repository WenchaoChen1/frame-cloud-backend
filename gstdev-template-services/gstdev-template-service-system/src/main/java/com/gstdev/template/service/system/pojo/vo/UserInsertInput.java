// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.template.common.base.BaseInsertInput;
import com.gstdev.template.common.constant.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserInsertInput extends BaseInsertInput {

  private String avatar;
  private String email;
  private Integer gender;
  @JsonFormat(pattern="yyyy-MM-dd")
  private Date lastLoginTime;
  private String mobile;
  private String password;
  private String username;
  private String accountName;
  private String tenantId;
  List<String> departIds;
  List<String> roleIds;
  private AccountTypeConstants accountTypeConstants=AccountTypeConstants.USER;
  private String firstName;
  private String lastName;
  private String icon;
}

