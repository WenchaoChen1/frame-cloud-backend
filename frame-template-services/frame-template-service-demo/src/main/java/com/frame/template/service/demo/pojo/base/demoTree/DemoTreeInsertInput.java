// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.pojo.base.demoTree;

import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DemoTreeInsertInput extends BaseTreeInsertInput {

    private String id;
    private Date createdDate;
    private String createdUser;
private String createdAccount;
    private Date updatedDate;
    private String updatedUser;
private String updatedAccount;
    private String code;
    private String name;

    private String parentId;
}

