package com.gstdev.cloud.service.system.pojo.base.SysPermission;

import com.gstdev.cloud.base.definition.domain.base.pojo.BaseDtoInterface;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermissionDto implements BaseDtoInterface<String> {

    private String permissionId;

    @Schema(title = "权限代码")
    private String permissionCode;

    @Schema(title = "权限名称")
    private String permissionName;
    private String permissionType;
//    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status;

    @Override
    public String getId() {
        return permissionId;
    }
}
