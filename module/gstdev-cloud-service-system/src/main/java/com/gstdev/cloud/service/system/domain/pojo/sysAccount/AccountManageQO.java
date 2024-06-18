package com.gstdev.cloud.service.system.domain.pojo.sysAccount;

import com.gstdev.cloud.data.core.annotations.Query;
import lombok.Data;

@Data
public class AccountManageQO {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query(type= Query.Type.INNER_LIKE)
    private String name;
    @Query(type= Query.Type.INNER_LIKE)
    private String identity;
}
