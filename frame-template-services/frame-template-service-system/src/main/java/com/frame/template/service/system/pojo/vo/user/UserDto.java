package com.frame.template.service.system.pojo.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserDto implements Serializable {
  String id;
  String username;
  String mobile;
  String email;
  String avatar;
  Integer gender;
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  Date lastLoginTime;
  Integer deleted;
}
