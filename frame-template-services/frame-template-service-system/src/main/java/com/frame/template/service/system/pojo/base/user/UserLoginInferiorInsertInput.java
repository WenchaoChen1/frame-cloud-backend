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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginInferiorInsertInput extends BaseUpdateInput {

  private String id;
  @Schema(title = "avatar 不能为空", required = true)
  private String avatar;
  @Schema(title = "deleted 不能为空", required = true)
  private Integer deleted;
  @Schema(title = "email 不能为空", required = true)
  private String email;
  @Schema(title = "gender 不能为空", required = true)
  private Integer gender;
  @Schema(title = "lastLoginTime 不能为空", required = true)
  private Date lastLoginTime;
  @Schema(title = "mobile 不能为空", required = true)
  private String mobile;
  @Schema(title = "password 不能为空", required = true)
  private String password;
  @Schema(title = "username 不能为空", required = true)
  private String username;
  @Schema(title = "accountTypeConstants 不能为空", required = true)
  private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
  private String firstName;
  private String lastName;
  private String icon;

}

