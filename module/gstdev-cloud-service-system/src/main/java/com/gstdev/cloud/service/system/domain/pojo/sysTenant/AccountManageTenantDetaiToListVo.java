// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.pojo.sysTenant;

import com.gstdev.cloud.service.system.TreeNode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountManageTenantDetaiToListVo extends TreeNode<String, AccountManageTenantDetaiToListVo> {

    private String id;
    private String tenantName;
    private String parentId;
    private Integer sort;
}
