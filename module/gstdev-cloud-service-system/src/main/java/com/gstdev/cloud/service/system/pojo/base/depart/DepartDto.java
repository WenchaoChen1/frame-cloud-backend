// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.base.depart;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.pojo.BaseTreeDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class DepartDto extends BaseTreeDto<DepartDto> {

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
    private Integer deleted;
    private String description;
    private String name;
    private String parentId;
    private String shortName;
    private Integer sort;
    private Integer status;
    private String tenantId;


}
