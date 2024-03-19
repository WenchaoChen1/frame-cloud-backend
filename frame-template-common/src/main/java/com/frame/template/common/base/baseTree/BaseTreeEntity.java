package com.frame.template.common.base.baseTree;

import com.frame.template.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseTreeEntity extends BaseEntity {
  @Column(name = "parent_id", length = 36, nullable = false)
  private String parentId;
}
