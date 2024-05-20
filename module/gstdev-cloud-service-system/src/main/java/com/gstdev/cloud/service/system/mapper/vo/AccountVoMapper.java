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
import com.gstdev.cloud.data.core.mapper.BaseVoMapper;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface AccountVoMapper extends BasePOJOMapper<SysAccount, AccountDto, AccountVo, AccountInsertInput, AccountUpdateInput> {

}

