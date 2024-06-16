package com.gstdev.cloud.service.system.pojo.o.sysAccount;

import com.gstdev.cloud.data.core.annotations.Query;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import lombok.Data;

@Data
public class AccountManageQO {

    private static final long serialVersionUID = 3163118978801722144L;

    @Query
    private String name;
}
