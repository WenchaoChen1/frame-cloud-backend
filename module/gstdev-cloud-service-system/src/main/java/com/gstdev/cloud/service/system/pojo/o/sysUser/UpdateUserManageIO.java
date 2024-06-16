package com.gstdev.cloud.service.system.pojo.o.sysUser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UpdateUserManageIO {

    private String id;
    private String username;
    private String phoneNumber;
    private String email;
    private Integer gender;
}
