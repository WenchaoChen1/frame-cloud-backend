package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.data.core.mapper.BaseDtoMapper;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.pojo.base.SysPermission.SysPermissionDto;
import com.gstdev.cloud.service.system.pojo.base.account.AccountDto;
import com.gstdev.cloud.service.system.pojo.base.account.AccountInsertInput;
import com.gstdev.cloud.service.system.pojo.base.account.AccountUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.SysPermission;
import com.gstdev.cloud.service.system.pojo.vo.account.AccountSaveInput;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SysPermissionMapper extends BaseDtoMapper<SysPermission, SysPermissionDto> {

}
