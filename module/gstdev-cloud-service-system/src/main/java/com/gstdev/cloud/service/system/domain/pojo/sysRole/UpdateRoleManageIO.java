package com.gstdev.cloud.service.system.domain.pojo.sysRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoleManageIO {

    private String id;
    private String code;
    private String description;
    private String parentId;
    private String roleName;
    private Integer sort;
    private Integer status;
    private String tenantId;
}