package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountManageIO {


    private String id;
    private String name;
    private String identity;
    private String tenantId;
    private String userId;
    private SysAccountType type;
}
