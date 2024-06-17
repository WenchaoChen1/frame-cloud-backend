package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserManageIO {

    private String id;
    private String username;
    private String phoneNumber;
    private String email;
    private Integer gender;
}
