// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.account;

import com.frame.template.common.constant.AccountTypeConstants;
import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AccountInsertInput extends BaseInsertInput implements Serializable {

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

