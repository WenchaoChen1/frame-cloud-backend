package com.frame.template.service.demo.pojo.domain;

import com.frame.template.common.persistence.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "test", schema = "public")
public class Test extends AbstractAuditingEntity {


    @Column(name = "name", length = 100)
    String name;
    @Column(name = "code", length = 100)
    String code;

}
