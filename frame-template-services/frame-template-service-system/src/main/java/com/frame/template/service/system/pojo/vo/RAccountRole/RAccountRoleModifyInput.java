package com.frame.template.service.system.pojo.vo.RAccountRole;

import com.frame.template.service.system.pojo.domain.Account;
import com.frame.template.service.system.pojo.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: 666
 * @date: 2022-12-06 11:31
 */
@Getter
@Setter
public class RAccountRoleModifyInput implements Serializable {
  private String id;
  private Account account;
  private Role role;
}
