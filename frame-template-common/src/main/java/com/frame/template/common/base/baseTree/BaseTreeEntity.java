package com.frame.template.common.base.baseTree;

import com.frame.template.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseTreeEntity extends BaseEntity {
  @Column(name = "parent_id", length = 36, nullable = false)
  private String parentId;
}
