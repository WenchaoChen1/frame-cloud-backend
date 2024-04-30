// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gstdev.cloud.data.core.entity.BaseTreeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sys_role", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE role SET deleted=1 WHERE id =?")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class Role extends BaseTreeEntity {

    @Column(name = "tenant_id", length = 50, nullable = false)
    private String tenantId;

    @Column(name = "parent_id", length = 36)
    private String parentId;

    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", nullable = false)
    private Integer deleted = 0;

//  @JsonIgnore
//  @OneToMany(mappedBy = "role",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//  private List<RRoleRTenantMenu> rRoleRTenantMenus;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "account_id", referencedColumnName = "id")
//  private Account account;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<RTenantMenu> rTenantMenus;

    @ManyToMany
    @JoinTable(name = "sys_r_account_role", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "account_id", referencedColumnName = "id")})
    private List<Account> accounts;
}



