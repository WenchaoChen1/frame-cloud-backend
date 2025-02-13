package com.frame.template.service.basic.security.domain;

import com.gstdev.cloud.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
@Table(name = "user", schema = "public")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "third_id", length = 60)
    private String thirdId;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "user_type", length = 60)
    private String userType;

    @Column(name = "user_from", length = 60)
    private String userFrom;

    @Column(name = "description")
    private String description;
}
