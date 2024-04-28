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
import java.io.Serializable;


@Getter
@Setter
public class DemoTemplateInsertInput {

    private String id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private String code;
    private String name;


}

