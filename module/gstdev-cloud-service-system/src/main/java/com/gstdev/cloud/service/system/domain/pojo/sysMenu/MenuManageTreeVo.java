// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.TreeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class MenuManageTreeVo extends TreeNode<String, MenuManageTreeVo> {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;

    private String name;
    private String menuName;
    private String code;
    private String parentId;
    private Integer sort;
    private DataItemStatus status;
    private String description;
    private Integer type;
    private String location;
    private String path;
    private String icon;
}

