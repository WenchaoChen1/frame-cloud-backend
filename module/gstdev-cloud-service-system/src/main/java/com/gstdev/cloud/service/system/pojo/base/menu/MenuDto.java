// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.base.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.base.core.utils.treeUtils.TreeNode;
import com.gstdev.cloud.data.core.pojo.BaseTreeDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class MenuDto extends BaseTreeDto<MenuDto> implements TreeNode<String, MenuDto> {

    private String id;
    private String code;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private String updatedBy;
    private String description;
    private Integer hidden;
    private String icon;
    private String name;
    private String parentId;
    private String path;
    private String permission;
    private Integer sort;
    private Integer status;
    private Integer tenantEnable;
    private Integer type;
    private String url;
    private String location;


    private List<String> checkedMenuId = new ArrayList<>();
    private List<String> halfCheckedMenuId = new ArrayList<>();

}

