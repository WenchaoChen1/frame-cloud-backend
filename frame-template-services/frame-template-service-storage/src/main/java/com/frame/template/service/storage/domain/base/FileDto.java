// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.storage.domain.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class FileDto extends BaseDto {

  private String id;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
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

