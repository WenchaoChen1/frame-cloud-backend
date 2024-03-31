package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.account.*;
import com.frame.template.service.system.pojo.domain.Account;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.BaseService;
import com.frame.template.service.system.pojo.base.account.*;

import java.util.List;

public interface AccountService extends BaseService<AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {
  List<AccountDto> findAllByUserId(String message);

//  boolean save(AccountSaveInput accountSaveInput);

  Account insert(Account account);

  Account insertAccountInitialization(AccountInsertInput accountInsertInput);

  Result<AccountDto> insertAccountInitializationToResult(AccountInsertInput accountInsertInput);

//    Integer findByTenantId(String tenantId);

  void deleteByTenantId(String tenantId);

}
