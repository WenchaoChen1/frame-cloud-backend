// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system.pojo.domain;

import com.frame.template.common.base.baseTree.BaseTreeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "tenant", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE tenant SET deleted=1 WHERE id =?")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class Tenant extends BaseTreeEntity {

  @Column(name = "parent_id", length = 36, nullable = false)
  private String parentId;

  @Column(name = "tenant_code", length = 50, nullable = false)
  private String tenantCode;

  @Column(name = "tenant_name", length = 100)
  private String tenantName;

  @Column(name = "description", length = 1000)
  private String description;

  @Column(name = "status")
  private Integer status = 0;

  @Column(name = "type")//comment 'development 0;platform 1;tenant 2'
  private Integer type = 0;

  @Column(name = "deleted")
  private Integer deleted = 0;

//-----------------自定义-----------

  @Column(name = "website")
  private String website;

  @Column(name = "address_line1")
  private String addressLine1;

  @Column(name = "address_line2")
  private String addressLine2;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "zip_code")
  private String zipCode;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email_address")
  private String emailAddress;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "logo")
  private String logo;

}
