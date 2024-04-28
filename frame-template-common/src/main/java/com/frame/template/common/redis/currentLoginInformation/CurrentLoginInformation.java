package com.frame.template.common.redis.currentLoginInformation;

import cn.hutool.json.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrentLoginInformation implements Serializable {
    private String userId;
    private String accountId;
    private String tenantId;
    private String accountType;
    private JSON tenant;
    private JSON currentLoginAccount;
    private JSON currentLoginAccountUserPermissions;
}
