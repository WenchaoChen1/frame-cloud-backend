package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.domain.base.account.*;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;

import java.util.List;

public interface SysAccountService extends BasePOJOService<SysAccount, String, AccountDto> {
    List<AccountDto> findAllByUserId(String message);

//  boolean save(AccountSaveInput accountSaveInput);

//    SysAccount insert(SysAccount account);

    SysAccount insertAccountManageInitialization(InsertAccountManageInitializationIO accountInsertInput);

    AccountDto insertAccountManageInitializationToDto(InsertAccountManageInitializationIO accountInsertInput);

//    Integer findByTenantId(String tenantId);

    void deleteByTenantId(String tenantId);

}
