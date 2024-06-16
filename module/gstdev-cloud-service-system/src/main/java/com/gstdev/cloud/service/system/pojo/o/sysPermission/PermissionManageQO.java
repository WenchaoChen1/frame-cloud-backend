package com.gstdev.cloud.service.system.pojo.o.sysPermission;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class PermissionManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query
    private String permissionName;
    @Query(type = Query.Type.IN)
    private Set<DataItemStatus> status;
    @Query(type = Query.Type.IN)
    private Set<String> permissionType;
}
