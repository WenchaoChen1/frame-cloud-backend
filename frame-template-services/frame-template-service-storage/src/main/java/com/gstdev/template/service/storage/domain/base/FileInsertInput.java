// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.storage.domain.base;

import com.gstdev.template.common.constant.FileConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

import com.gstdev.template.common.base.BaseInsertInput;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

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

