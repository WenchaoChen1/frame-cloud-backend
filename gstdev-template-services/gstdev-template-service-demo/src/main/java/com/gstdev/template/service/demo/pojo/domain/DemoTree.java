package com.gstdev.template.service.demo.pojo.domain;


import com.gstdev.template.common.base.baseTree.BaseTreeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "demo_tree", schema = "public")
public class DemoTree extends BaseTreeEntity {

  @Column(name = "name", length = 100)
  String name;
  @Column(name = "code", length = 100)
  String code;

}
