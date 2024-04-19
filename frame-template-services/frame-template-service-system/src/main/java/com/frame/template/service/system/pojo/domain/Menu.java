// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frame.template.common.base.baseTree.BaseTreeEntity;
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
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "menu", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE menu SET deleted=1 WHERE id =?")
public class Menu extends BaseTreeEntity {

  @Column(name = "deleted", nullable = false)
  private Integer deleted = 0;

  @Column(name = "parent_id", length = 36, nullable = false)
  private String parentId;

  @Column(name = "name", length = 64, nullable = false)
  private String name;

  @Column(name = "code", length = 64, nullable = false, unique = true)
  private String code;

  @Column(name = "icon", length = 100)
  private String icon;

  @Column(name = "path", length = 100)
  private String path;

  @Column(name = "hidden")
  private Integer hidden = 0;

  @Column(name = "sort", length = 6)
  private Integer sort;

  @Column(name = "description")
  private String description;


  @Column(name = "url", length = 100)
  private String url;

  @Column(name = "permission", length = 50)
  private String permission;

  @Column(name = "type", nullable = false)
  private Integer type = 0;

  @Column(name = "status", nullable = false)
  private Integer status = 0;

  @Column(name = "tenant_enable")
  private Integer tenantEnable = 0;

  @Column(name = "location", length = 16)
  private String location = "LEFT-MENU";


  @JsonIgnore
  @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
  private List<RTenantMenu> rTenantMenus;
//
//  @JsonIgnore
//  @OneToMany(mappedBy = "menu",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//  private List<RRoleRTenantMenu> rRoleMenus;
}
