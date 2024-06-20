// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserManagePageVo {

    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private DataItemStatus status;
    private String description;
}

