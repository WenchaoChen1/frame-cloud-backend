// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.account;

import com.frame.template.common.base.BaseInsertInput;
import com.frame.template.common.constant.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountInsertInput extends BaseInsertInput {

  List<String> departIds;
  List<String> roleIds;
  private String tenantId;
  private String userId;
  private String name;
  private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;

  public String getType() {
    return accountTypeConstants.getCode();
  }
}

