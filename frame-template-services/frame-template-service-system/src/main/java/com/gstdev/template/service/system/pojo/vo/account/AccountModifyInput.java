package com.gstdev.template.service.system.pojo.vo.account;

import com.gstdev.template.service.system.pojo.vo.user.UserModifyInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountModifyInput {
  String id;
  String tenantId;
  String identity;

  UserModifyInput user;
  List<String> depart;
  List<String> role;
}
