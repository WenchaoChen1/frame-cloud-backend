package com.gstdev.cloud.service.system.pojo.o.sysRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertAndUpdateRoleManageIO {

    private String id;
    private String code;
    private String description;
    private String parentId;
    private String roleName;
    private Integer sort;
    private Integer status;
    private String tenantId;
}
