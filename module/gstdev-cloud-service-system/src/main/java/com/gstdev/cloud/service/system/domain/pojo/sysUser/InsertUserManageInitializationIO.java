package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertUserManageInitializationIO {

    private String username;
    private String phoneNumber;
    private String email;
    private Integer gender = 0;
    private String nickname;
    private String avatar;
    private DataItemStatus status;

    private String firstName;
    private String lastName;

    List<String> departIds;
    List<String> roleIds;
    private String accountName;
    private String tenantId;
    private SysAccountType type = SysAccountType.USER;
    private String icon;

}
