package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.account.*;
import com.frame.template.service.system.pojo.domain.Account;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOService;

import java.util.List;

public interface AccountService extends BasePOJOService<Account, String, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {
    List<AccountDto> findAllByUserId(String message);

//  boolean save(AccountSaveInput accountSaveInput);

    Account insert(Account account);

    Account insertAccountInitialization(AccountInsertInput accountInsertInput);

    Result<AccountDto> insertAccountInitializationToResult(AccountInsertInput accountInsertInput);

//    Integer findByTenantId(String tenantId);

    void deleteByTenantId(String tenantId);

}
