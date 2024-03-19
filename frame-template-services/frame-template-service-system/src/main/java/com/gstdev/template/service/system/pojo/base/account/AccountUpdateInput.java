// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.account;

import com.gstdev.template.common.base.BaseUpdateInput;
import com.gstdev.template.common.constant.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateInput extends BaseUpdateInput {

  private String id;
  private String name;
//  private String type;
  private AccountTypeConstants accountTypeConstants=AccountTypeConstants.USER;
  public String getType() {
    return accountTypeConstants.getCode();
  }
}

