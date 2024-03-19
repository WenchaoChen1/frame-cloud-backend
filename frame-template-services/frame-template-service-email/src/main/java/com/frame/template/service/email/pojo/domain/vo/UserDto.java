package com.frame.template.service.email.pojo.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
  private String id;
  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String activateToken;
  private String customerName;
}
