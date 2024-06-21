// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysPermission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PermissionManagePageVo {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    private Date updatedDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedUser;
    private String updatedAccount;
    private String permissionId;
    private String permissionCode;
    private String permissionName;
    private String permissionType;
    private DataItemStatus status;
}

