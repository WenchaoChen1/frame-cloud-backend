// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.account;

import com.gstdev.template.common.base.BaseInsertInput;
import com.gstdev.template.common.constant.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountInsertInput extends BaseInsertInput {

  private String tenantId;
  private String userId;
  private String name;
  List<String> departIds;
  List<String> roleIds;
  private AccountTypeConstants accountTypeConstants=AccountTypeConstants.USER;

  public String getType() {
    return accountTypeConstants.getCode();
  }
}

