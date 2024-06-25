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
import com.gstdev.cloud.service.system.domain.enums.SysMenuLocation;
import com.gstdev.cloud.service.system.domain.enums.SysMenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    private SysMenuType type;
    @Column(name = "location", nullable = false)
    private SysMenuLocation location;

    @Schema(title = "数据状态")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;

    @JsonIgnore
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<SysTenantMenu> rTenantMenus;

}
