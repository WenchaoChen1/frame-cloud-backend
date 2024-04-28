// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.domain.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FileInsertInput implements Serializable {
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
    private String tableType;
    private Integer state;
}

