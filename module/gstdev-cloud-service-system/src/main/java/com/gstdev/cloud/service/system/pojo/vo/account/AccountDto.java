package com.gstdev.cloud.service.system.pojo.vo.account;

import com.gstdev.cloud.service.system.pojo.vo.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AccountDto implements Serializable {
    String id;
    String identity;
    UserDto user;
    String tenantId;

    List<String> depart;
    List<String> role;
}
