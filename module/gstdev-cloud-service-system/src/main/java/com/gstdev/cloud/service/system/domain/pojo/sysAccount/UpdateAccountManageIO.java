package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.service.system.domain.enums.SysAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountManageIO {


    private String id;
    private String name;
    private String tenantId;
    private SysAccountType type;
}
