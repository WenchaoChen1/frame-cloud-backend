// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.frame.template.service.system.feign.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frame.template.common.base.BaseVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class FileVo extends BaseVo {

  private String id;
  @JsonFormat(timezone = "America/Chicago", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "America/Chicago", pattern = "yyyy-MM-dd")
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

