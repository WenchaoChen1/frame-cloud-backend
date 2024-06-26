package com.frame.template.service.email.pojo.entity;

import com.frame.template.common.persistence.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "email")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class Email extends AbstractAuditingEntity {

    @Column(name = "type")
    private int type;

    @Column(name = "sender_email", nullable = false, length = 200)
    private String senderEmail;

    @Column(name = "receiver_email", nullable = false, length = 200)
    private String receiverEmail;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "body", nullable = false, length = 1000)
    private String body;

    @Column(name = "token")
    private String token;

    @Column(name = "status", nullable = false)
    private int Status;

    @Column(name = "is_deleted")
    private Boolean deleted = false;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
