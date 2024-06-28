// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysBusinessPermission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.util.TreeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class BusinessPermissionManageTreeVo extends TreeNode<String, BusinessPermissionManageTreeVo> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String businessPermissionId;
    private String parentId;
    private String name;
    private String code;
    private DataItemStatus status;
    private Integer sort;
    private String description;
    private String tenantId;

    public void setBusinessPermissionId(String businessPermissionId) {
        this.businessPermissionId = businessPermissionId;
        super.setId(businessPermissionId);
    }
}

