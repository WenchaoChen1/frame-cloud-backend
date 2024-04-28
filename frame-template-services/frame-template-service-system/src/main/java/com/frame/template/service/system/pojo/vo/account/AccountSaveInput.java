package com.frame.template.service.system.pojo.vo.account;

import com.frame.template.service.system.pojo.vo.user.UserSaveInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountSaveInput {
    String identity;
    String tenantId;

    UserSaveInput user;

    List<String> depart;

    List<String> role;
}
