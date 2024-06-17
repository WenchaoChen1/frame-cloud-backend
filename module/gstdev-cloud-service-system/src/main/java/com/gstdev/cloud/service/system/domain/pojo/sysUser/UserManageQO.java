package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.annotations.Query;
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
