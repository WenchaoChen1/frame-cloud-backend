// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.util.List;


/**
 * <p>Description: 前端系统菜单 </p>
 */
@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_menu", schema = "public")
//@Where(clause = "deleted = 0")
//@SQLDelete(sql = "UPDATE sys_menu SET deleted=1 WHERE id =?")
public class SysMenu extends BaseTreeEntity {

//    @Column(name = "deleted", nullable = false)
//    private Integer deleted = 0;

    @Column(name = "parent_id", length = 36, nullable = false)
    private String parentId;

    @Column(name = "menu_name", length = 64, nullable = false)
    private String menuName;
    @Column(name = "code", length = 64, nullable = false, unique = true)
    private String code;


    @Column(name = "name", length = 150)
    private String name;
    @Column(name = "path", length = 250)
    private String path;
    @Column(name = "icon", length = 100)
    private String icon;


    @Column(name = "sort", length = 6)
    private Integer sort;
    @Column(name = "description")
    private String description;
    @Column(name = "type", nullable = false)
    private Integer type = 0;
    @Column(name = "location", length = 16)
    private String location = "LEFT-MENU";
    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

//    @Column(name = "tenant_enable")
//    private Integer tenantEnable = 0;

//    //可以在菜单中不展示这个路由，包括子路由
//    private Boolean ideInMenu = true;
//    //可以在面包屑中不展示这个路由，包括子路由
//    private Boolean hideInBreadcrumb = true;
//    //当前路由不展示顶栏header
//    private Boolean headerRender = false;
//    //当前路由不展示页脚footer
//    private Boolean footerRender = false;
//    //当前路由不展示菜单;点击后隐藏导航菜单
//    private Boolean menuRender = false;
//    //当前路由不展示菜单顶栏
//    private Boolean menuHeaderRender = false;
//    //子项往上提，只是不展示父菜单
//    private Boolean flatMenu = true;


//
//    @Column(name = "hidden")
//    private Integer hidden = 0;
//    @Column(name = "url", length = 100)
//    private String url;
//    @Column(name = "permission", length = 50)
//    private String permission;


    @JsonIgnore
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<SysTenantMenu> rTenantMenus;
//
//  @JsonIgnore
//  @OneToMany(mappedBy = "menu",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//  private List<RRoleRTenantMenu> rRoleMenus;
}
