package com.gstdev.template.service.system.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.BaseService;
import com.gstdev.template.service.system.pojo.base.account.*;
import com.gstdev.template.service.system.pojo.domain.Account;
import com.gstdev.template.service.system.pojo.vo.account.AccountSaveInput;

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
