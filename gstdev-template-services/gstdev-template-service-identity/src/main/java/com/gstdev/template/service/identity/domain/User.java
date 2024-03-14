package com.gstdev.template.service.identity.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB001_USER")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class User {
  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(name = "TB001_Id")
  private String id;

  @Column(name = "TB001_Password", length = 500)
  private String password;

  @Column(name = "TB001_UserName")
  private String username;

  @Column(name = "TB001_Email", nullable = false,unique= true)
  private String email;
  @Column(name = "user_id")
  private String userId;

}
