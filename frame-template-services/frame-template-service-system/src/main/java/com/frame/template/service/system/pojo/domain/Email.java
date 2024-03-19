package com.frame.template.service.system.pojo.domain;

import com.frame.template.common.persistence.AbstractAuditingEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
