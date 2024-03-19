// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.storage.domain.base;

import lombok.Getter;
import lombok.Setter;

import com.frame.template.common.base.BaseInsertInput;

@Getter
@Setter
public class FileInsertInput extends BaseInsertInput {
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

