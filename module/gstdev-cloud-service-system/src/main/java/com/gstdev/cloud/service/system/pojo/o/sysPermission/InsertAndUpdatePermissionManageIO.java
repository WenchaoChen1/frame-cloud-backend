package com.gstdev.cloud.service.system.pojo.o.sysPermission;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertAndUpdatePermissionManageIO {

    private String permissionId;
    private String permissionCode;
    private String permissionName;
    private String permissionType;
    @Enumerated(EnumType.ORDINAL)
    private DataItemStatus status = DataItemStatus.ENABLE;
}
