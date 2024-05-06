package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.pojo.base.account.AccountDto;
import com.gstdev.cloud.service.system.pojo.base.account.AccountInsertInput;
import com.gstdev.cloud.service.system.pojo.base.account.AccountUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.account.AccountSaveInput;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface AccountMapper extends BasePOJOMapper<SysAccount, AccountDto, AccountInsertInput, AccountUpdateInput> {

    SysAccount toSave(AccountSaveInput saveInput);
}
