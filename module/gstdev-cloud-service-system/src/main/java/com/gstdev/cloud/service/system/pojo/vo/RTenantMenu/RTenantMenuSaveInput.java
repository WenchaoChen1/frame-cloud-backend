// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.vo.RTenantMenu;

import com.gstdev.cloud.service.system.pojo.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class RTenantMenuSaveInput implements Serializable {
    private String tenantId;
    private Menu menu;
    private Integer status;
}
