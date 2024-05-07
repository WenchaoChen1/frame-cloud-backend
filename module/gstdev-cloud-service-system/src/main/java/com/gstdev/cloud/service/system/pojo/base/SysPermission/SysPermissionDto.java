package com.gstdev.cloud.service.system.pojo.base.SysPermission;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermissionDto implements BaseDtoInterface<String> {

    private String permissionId;

    @Schema(name = "权限代码")
    private String permissionCode;

    @Schema(name = "权限名称")
    private String permissionName;

    @Override
    public String getId() {
        return permissionId;
    }
}
