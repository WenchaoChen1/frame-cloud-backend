// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysMenu;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuManageDetailVo {

    private String id;
    private String roleName;
    private String code;
    private String tenantId;
    private String parentId;
    private Integer sort;
    private DataItemStatus status;
    private String description;

}
