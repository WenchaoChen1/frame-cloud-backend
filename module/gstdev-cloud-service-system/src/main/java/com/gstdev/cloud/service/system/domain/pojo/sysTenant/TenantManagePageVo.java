// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.pojo.BaseTreeVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TenantManagePageVo extends BaseTreeVo {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private String updatedBy;
    private String parentId;
    private String tenantCode;
    private String tenantName;
    private String description;
    private Integer status;
    private Integer type;
}

