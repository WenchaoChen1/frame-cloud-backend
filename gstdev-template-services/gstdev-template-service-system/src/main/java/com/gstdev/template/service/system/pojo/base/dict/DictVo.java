// ====================================================
//
// This file is part of the Riching Cloud Platform.
//
// Create by Riching Tech <support@richingtech.com>
// Copyright (c) 2020-2025 richingtech.com
//
// ====================================================

package com.gstdev.template.service.system.pojo.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.template.common.base.baseTree.BaseTreeVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class DictVo extends BaseTreeVo {

  private String id;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;
  private String createdBy;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;
  private String updatedBy;
  private String code;
  private String description;
  private String name;
  private String parentId;
  private Integer sort;
  private Integer status;


}

