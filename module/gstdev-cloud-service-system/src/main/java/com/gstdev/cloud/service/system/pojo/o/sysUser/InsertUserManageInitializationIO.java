package com.gstdev.cloud.service.system.pojo.o.sysUser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.pojo.BaseInsertInput;
import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InsertUserManageInitializationIO {

        private String username;
        private String email;
        private String phoneNumber;
        private String password;
        private String nickname;
        private String avatar;
        private Integer gender = 0;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date lastLoginTime;


        List<String> departIds;
        List<String> roleIds;
        private String accountName;
        private String tenantId;
        private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;
        private String firstName;
        private String lastName;
        private String icon;

        private DataItemStatus status;
}
