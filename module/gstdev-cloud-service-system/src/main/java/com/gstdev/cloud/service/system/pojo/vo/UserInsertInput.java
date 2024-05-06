// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import com.gstdev.cloud.service.system.constants.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserInsertInput extends BaseInsertInput implements Serializable {

    List<String> departIds;
    List<String> roleIds;
    private String avatar;
    private String email;
    private Integer gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
    private String mobile;
    private String password;
    private String username;
    private String accountName;
    private String tenantId;
    private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
    private String firstName;
    private String lastName;
    private String icon;
}
