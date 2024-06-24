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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "sys_tenant_menu", schema = "public")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class SysTenantMenu extends BasePOJOEntity {

    @Column(name = "tenantId", length = 36, nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private SysMenu menu;

    @Column(name = "status")
    private Integer status;

    /**
     * 0半选 1全选
     */
    @Column(name = "checked", length = 1)
    private Integer checked;

    @JsonIgnore
    @ManyToMany(mappedBy = "tenantMenus")
    private List<SysRole> roles;
}
