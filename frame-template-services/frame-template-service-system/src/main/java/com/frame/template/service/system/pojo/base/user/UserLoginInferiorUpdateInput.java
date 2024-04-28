// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.user;

import com.frame.template.common.constant.AccountTypeConstants;
import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginInferiorUpdateInput extends BaseUpdateInput {

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
  private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
  private String firstName;
  private String lastName;
  private String icon;
}

