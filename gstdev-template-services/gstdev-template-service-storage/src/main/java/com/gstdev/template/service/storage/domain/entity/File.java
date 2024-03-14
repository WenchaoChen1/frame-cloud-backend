// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.gstdev.template.service.storage.domain.entity;

import com.gstdev.template.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "File")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE tenant SET deleted=1 WHERE id =?")
@NoArgsConstructor
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class File extends BaseEntity {

  @Column(name = "hash", length = 1024)
  private String hash;

  @Column(name = "bucket_name")
  private String bucketName;

  @Column(name = "name", length = 1024)
  private String name;

  @Column(name = "original_name")
  private String originalName;

  @Column(name = "link", length = 1024)
  private String link;

  @Column(name = "content_type")
  private String contentType;

  @Column(name = "length")
  private long length;

  @Column(name = "etag")
  private String etag;

  @Column(name = "tenant_id")
  private String tenantId;

  @Column(name = "services")
  private String services;

  @Column(name = "table_code")
  private String tableCode;

  @Column(name = "deleted")
  private Integer deleted = 0;

  @Column(name = "state")//0成功、1失败
  private Integer state;

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
