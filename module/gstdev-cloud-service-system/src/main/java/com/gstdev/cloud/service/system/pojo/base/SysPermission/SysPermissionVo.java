package com.gstdev.cloud.service.system.pojo.base.SysPermission;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermissionVo {

    private String permissionId;

    @Schema(name = "权限代码")
    private String permissionCode;

    @Schema(name = "权限名称")
    private String permissionName;

}
