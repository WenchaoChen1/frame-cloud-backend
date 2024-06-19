package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
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
    private String nickname;
    private String avatar;
    private DataItemStatus status;

    private String firstName;
    private String lastName;
}
