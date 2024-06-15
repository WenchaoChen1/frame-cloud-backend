package com.gstdev.cloud.service.system.pojo.o.sysUser;

import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

@Data
public class UserManageQO {

    private static final long serialVersionUID = 3163118978801722144L;
    private DataItemStatus status;
}
