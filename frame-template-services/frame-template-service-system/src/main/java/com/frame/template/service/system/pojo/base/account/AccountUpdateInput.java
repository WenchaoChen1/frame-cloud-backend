// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.base.account;

import com.frame.template.common.constant.AccountTypeConstants;
import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateInput extends BaseUpdateInput {

    private String id;
    private String name;
    //  private String type;
    private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;

    public String getType() {
        return accountTypeConstants.getCode();
    }
}

