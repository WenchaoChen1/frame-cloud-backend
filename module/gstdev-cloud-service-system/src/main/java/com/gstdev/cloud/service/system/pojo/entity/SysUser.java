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
import com.gstdev.cloud.data.core.entity.BasePOJOEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "sys_user", schema = "public")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE public.sys_user SET deleted=1 WHERE id =?")
public class SysUser extends BasePOJOEntity {
    @Schema(title = "用户名")
    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;

    @Schema(title = "EMAIL")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Schema(title = "手机号码")
    @Column(name = "phone_number", length = 256)
    private String phoneNumber;

    @Schema(title = "密码", description = "BCryptPasswordEncoder")
    @Column(name = "password", length = 500)
    private String password;

    @Schema(title = "昵称")
    @Column(name = "nick_name", length = 64)
    private String nickname;

    @Schema(title = "头像")
    @Column(name = "avatar", length = 36)
    private String avatar;

//    @Column(name = "mobile", length = 20)
//    private String mobile;

    @Schema(title = "性别")
    @Column(name = "gender", length = 1)
    private Integer gender = 0;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "deleted", nullable = false)
    private Integer deleted = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<SysAccount> account;

    //-----------------自定义-----------
    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(name = "activate_token", length = 255)
    private String activateToken;

    @Column(name = "status")
    private Integer status = 0;
}
