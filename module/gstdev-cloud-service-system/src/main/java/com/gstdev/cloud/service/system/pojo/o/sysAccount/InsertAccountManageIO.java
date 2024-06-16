package com.gstdev.cloud.service.system.pojo.o.sysAccount;

import com.gstdev.cloud.service.system.enums.AccountTypeConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertAccountManageIO {


    private String id;
    private String name;
    //  private String type;
    private AccountTypeConstants accountTypeConstants = AccountTypeConstants.USER;

    public String getType() {
        return accountTypeConstants.getCode();
    }
}
