// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.template.common.constant.AccountTypeConstants;
import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateInput extends BaseUpdateInput {

    private String id;
    private String avatar;
    private Integer deleted;
    private String email;
    private Integer gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
    private String mobile;
    private String password;
    private String username;
    private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
    private String firstName;
    private String lastName;
    private String icon;
}

