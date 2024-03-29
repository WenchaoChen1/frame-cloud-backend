package com.frame.template.service.system.mapper;

import com.frame.template.common.base.BaseMapper;
import com.frame.template.service.system.pojo.base.account.AccountDto;
import com.frame.template.service.system.pojo.base.account.AccountInsertInput;
import com.frame.template.service.system.pojo.base.account.AccountUpdateInput;
import com.frame.template.service.system.pojo.vo.account.AccountSaveInput;
import com.frame.template.service.system.pojo.domain.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface AccountMapper extends BaseMapper<Account, AccountDto, AccountInsertInput, AccountUpdateInput> {

  Account toSave(AccountSaveInput saveInput);
}
