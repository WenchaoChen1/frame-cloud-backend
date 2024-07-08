package com.frame.template.common.redis.currentLoginInformation;

import cn.hutool.json.JSON;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
    private List<Object> leftAndTopRoutes;
    private List<Object> rightRoutes;
    private List<Object> functionPermissionCode;


}
