package com.frame.template.service.demo.pojo.domain;

import com.frame.template.common.base.BaseEntity;
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
@Table(name = "test", schema = "public")
public class Test extends BaseEntity {


  @Column(name = "name", length = 100)
  String name;
  @Column(name = "code", length = 100)
  String code;

}
