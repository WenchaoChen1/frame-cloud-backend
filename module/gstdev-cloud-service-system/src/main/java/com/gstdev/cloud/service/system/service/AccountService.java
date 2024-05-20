package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.pojo.base.account.*;

import java.util.List;

public interface AccountService extends BasePOJOService<SysAccount, String, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {
    List<AccountDto> findAllByUserId(String message);

//  boolean save(AccountSaveInput accountSaveInput);

//    SysAccount insert(SysAccount account);

    SysAccount insertAccountInitialization(AccountInsertInput accountInsertInput);

    Result<AccountDto> insertAccountInitializationToResult(AccountInsertInput accountInsertInput);

//    Integer findByTenantId(String tenantId);

    void deleteByTenantId(String tenantId);

}
