package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.service.system.domain.base.account.AccountDto;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.SwitchUserAccountDetailDto;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.UpdateAccountManageIO;

import java.util.List;

public interface SysAccountService extends BaseService<SysAccount, String> {

    List<SysAccount> findAllByUserId(String userId);
    SysAccount insertAccountManageInitialization(InsertAccountManageInitializationIO accountInsertInput);

    AccountDto insertAccountManageInitializationToDto(InsertAccountManageInitializationIO accountInsertInput);

    void deleteByTenantId(String tenantId);

    void insertAccountManage(InsertAccountManageIO insertAccountManageIO);

    void updateAccountManage(UpdateAccountManageIO updateAccountManageIO);

    List<SwitchUserAccountDetailDto> getSwitchUserAccountDetail();

}
