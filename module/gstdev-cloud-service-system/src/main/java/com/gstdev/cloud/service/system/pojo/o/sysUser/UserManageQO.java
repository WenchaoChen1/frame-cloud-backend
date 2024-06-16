package com.gstdev.cloud.service.system.pojo.o.sysUser;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query
    private String username;
    @Query
    private String email;
    @Query
    private String phoneNumber;
}
