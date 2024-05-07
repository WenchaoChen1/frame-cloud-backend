package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.user.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.pojo.vo.user.UserModifyInput;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper extends BasePOJOMapper<SysUser, UserDto, UserInsertInput, UserUpdateInput> {
    /**
     * 复制
     *
     * @param modifyInput
     * @param user
     */
    void copyModify(UserModifyInput modifyInput, @MappingTarget SysUser user);

    void copyModify(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput, @MappingTarget SysUser user);

    List<AccountListDto> accountListToDto(List<SysAccount> accountList);
}
