package com.gstdev.cloud.service.system.domain.pojo.sysUser;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserManageQO implements Serializable {

    private static final long serialVersionUID = 3163118978801722144L;
    @Query(type=Query.Type.INNER_LIKE)
    private String username;
    @Query(type=Query.Type.INNER_LIKE)
    private String email;
    @Query(type=Query.Type.INNER_LIKE)
    private String phoneNumber;
}
