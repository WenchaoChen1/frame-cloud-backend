// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.base.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class AccountDto extends BaseDto {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private String updatedBy;
    private Integer deleted;
    private String identity;
    private String tenantId;
    private String name;
    private String type;
    private UserDto user;

    public String getUserId() {
        return user.getId();
    }

    public AccountTypeConstants getAccountTypeConstants() {
        return AccountTypeConstants.getAccountTypeConstants(this.type);
    }
}

