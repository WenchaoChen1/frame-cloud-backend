// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.demo.pojo.vo.demoTemplate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemoTemplateUpdateInput {

    private String id;
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String code;
    private String name;


}

