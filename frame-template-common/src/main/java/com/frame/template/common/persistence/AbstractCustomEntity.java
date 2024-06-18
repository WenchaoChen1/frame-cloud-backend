// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.common.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractCustomEntity {

    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @JsonIgnore
    @Column(name = "created_by", length = 36, updatable = false)
    private String createdUser;
private String createdAccount;

    @JsonIgnore
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "updated_at")
    private Instant updatedAt;
    @JsonIgnore
    @Column(name = "updated_by", length = 36)
    private String updatedUser;
private String updatedAccount;

    @PrePersist
    public void addAuditInfo() {
        createdAt = Instant.now();
//    createdBy = SecurityUtils.getUserId();
        updatedAt = Instant.now();
//    updatedBy = SecurityUtils.getUserId();
    }

    @PreUpdate
    public void updateAuditInfo() {
        updatedAt = Instant.now();
//    updatedBy = SecurityUtils.getUserId();
    }

}
