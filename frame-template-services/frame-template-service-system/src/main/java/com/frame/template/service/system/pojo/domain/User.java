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
import com.frame.template.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author zhucy
 */
@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "user", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE public.user SET deleted=1 WHERE id =?")
public class User extends BaseEntity {
  @Column(name = "username", length = 30)
  private String username;

  @Column(name = "mobile", length = 20)
  private String mobile;

  @Column(name = "email", length = 100, nullable = false,unique= true)
  private String email;

  @Column(name = "avatar", length = 36)
  private String avatar;

  @Column(name = "gender", length = 1)
  private Integer gender = 0;

  @Column(name = "last_login_time")
  private Date lastLoginTime;

  @Column(name = "deleted", nullable = false)
  private Integer deleted = 0;

  @Column(name = "password", length = 500)
  private String password;

  @Column(name = "icon")
  private String icon;

  @JsonIgnore
  @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  private List<Account> account;

  //-----------------自定义-----------
    @Column(name="first_name",length = 60)
  private String firstName;

  @Column(name="last_name",length = 60)
  private String lastName;

  @Column(name = "activate_token", length = 255)
  private String activateToken;

  @Column(name = "status")
  private Integer status = 0;
}
