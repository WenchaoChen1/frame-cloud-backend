// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frame.template.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "r_tenant_menu", schema = "public")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class RTenantMenu extends BaseEntity {

  @Column(name = "tenantId", length = 36, nullable = false)
  private String tenantId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "menu_id", referencedColumnName = "id")
  private Menu menu;

  @Column(name = "status")
  private Integer status;

  /**
   * 0半选 1全选
   */
  @Column(name = "checked", length = 1)
  private Integer checked;


  @ManyToMany
  @JoinTable(name = "r_role_r_tenant_menu", joinColumns = {
    @JoinColumn(name = "r_tenant_menu_id", referencedColumnName = "id")}, inverseJoinColumns = {
    @JoinColumn(name = "role_id", referencedColumnName = "id")})
  private List<Role> roles;
}
