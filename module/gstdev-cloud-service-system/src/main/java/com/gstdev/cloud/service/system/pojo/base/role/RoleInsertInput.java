// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.base.role;

import com.gstdev.cloud.data.core.pojo.BaseTreeInsertInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleInsertInput extends BaseTreeInsertInput {

    List<String> menuIds;
    private String id;
    private String code;
    private String description;
    private String parentId;
    private String roleName;
    private Integer sort;
    private Integer status;
    private String tenantId;

}
