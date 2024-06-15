// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by gstdev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.service.system.mapper.vo;

import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.pojo.base.account.AccountDto;
import com.gstdev.cloud.service.system.pojo.base.account.AccountInsertInput;
import com.gstdev.cloud.service.system.pojo.base.account.AccountUpdateInput;
import com.gstdev.cloud.service.system.pojo.base.account.AccountVo;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAndUpdateAccountManageIO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysAccountMapper extends BasePOJOMapper<SysAccount, AccountDto, AccountVo, AccountInsertInput, AccountUpdateInput> {
    SysAccount toEntity(InsertAccountManageInitializationIO insertAccountManageInput);

    void copy(InsertAndUpdateAccountManageIO insertAndUpdateAccountManageIO, @MappingTarget SysAccount sysAccount);

}

