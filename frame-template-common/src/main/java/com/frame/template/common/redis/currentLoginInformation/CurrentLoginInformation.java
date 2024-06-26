package com.frame.template.common.redis.currentLoginInformation;

import cn.hutool.json.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrentLoginInformation implements Serializable {
    private String userId;
    private String userName;
    private String accountId;
    private String accountName;
    private String tenantId;
    private Integer type;
    private JSON tenant;
    private JSON currentLoginAccount;
    private JSON currentLoginAccountUserPermissions;

}
