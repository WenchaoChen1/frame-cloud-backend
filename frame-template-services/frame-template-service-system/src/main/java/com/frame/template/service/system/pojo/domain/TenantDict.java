// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.domain;

import com.frame.template.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "tenant_dict", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE tenant_dict SET deleted=1 WHERE id =?")
public class TenantDict extends BaseEntity {
  @Column(name = "name", length = 64, nullable = false)
  private String name;

  @Column(name = "parent_id", length = 36)
  private String parentId;

  @Column(name = "tenant_id", length = 50, nullable = false)
  private String tenantId;

  @Column(name = "code", length = 50, nullable = false)
  private String code;

  @Column(name = "status", nullable = false)
  private Integer status = 1;

  @Column(name = "sort", length = 6)
  private Integer sort;

  @Column(name = "description")
  private String description;

  @Column(name = "deleted", nullable = false)
  private Integer deleted = 0;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dict_id", referencedColumnName = "id")
  private Dict dict;
}
