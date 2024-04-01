// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.user;

import com.frame.template.common.base.BaseUpdateInput;
import com.frame.template.common.constant.AccountTypeConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginInferiorInsertInput extends BaseUpdateInput {

  private String id;
  @ApiModelProperty(value = "avatar 不能为空", required = true)
  private String avatar;
  @ApiModelProperty(value = "deleted 不能为空", required = true)
  private Integer deleted;
  @ApiModelProperty(value = "email 不能为空", required = true)
  private String email;
  @ApiModelProperty(value = "gender 不能为空", required = true)
  private Integer gender;
  @ApiModelProperty(value = "lastLoginTime 不能为空", required = true)
  private Date lastLoginTime;
  @ApiModelProperty(value = "mobile 不能为空", required = true)
  private String mobile;
  @ApiModelProperty(value = "password 不能为空", required = true)
  private String password;
  @ApiModelProperty(value = "username 不能为空", required = true)
  private String username;
  @ApiModelProperty(value = "accountTypeConstants 不能为空", required = true)
  private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
  private String firstName;
  private String lastName;
  private String icon;

}

