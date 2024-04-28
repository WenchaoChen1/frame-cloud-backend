// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.vo.RTenantMenu;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class RTenantMenuBatchSaveInput implements Serializable {
    private String tenantId;
    private List<String> checkedMenuId;
    private List<String> halfCheckedMenuId;
}

