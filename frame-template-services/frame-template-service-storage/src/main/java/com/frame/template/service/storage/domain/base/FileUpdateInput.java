// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.domain.base;

import com.gstdev.cloud.data.core.pojo.BaseUpdateInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileUpdateInput extends BaseUpdateInput {

    private String id;
    private Date createdDate;
    private String createdUser;
    private String createdAccount;
    private Date updatedDate;
    private String updatedUser;
    private String updatedAccount;
    private String bucketName;
    private String contentType;
    private String etag;
    private String hash;
    private Long length;
    private String link;
    private String name;
    private String originalName;
    private String tenantId;
    private String services;
    private String tableCode;
    private Integer state;

}

