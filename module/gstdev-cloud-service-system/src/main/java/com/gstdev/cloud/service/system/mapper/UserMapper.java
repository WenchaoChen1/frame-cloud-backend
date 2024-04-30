package com.gstdev.cloud.service.system.mapper;

import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.User;
import com.gstdev.cloud.service.system.pojo.vo.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.pojo.vo.user.UserModifyInput;
import com.gstdev.cloud.service.system.pojo.entity.Account;
import com.gstdev.cloud.data.core.mapper.BasePOJOMapper;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.gstdev.cloud.service.system.pojo.entity.Account;
import com.gstdev.cloud.service.system.pojo.entity.User;
import com.gstdev.cloud.service.system.pojo.vo.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.pojo.vo.user.UserModifyInput;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper extends BasePOJOMapper<User, UserDto, UserInsertInput, UserUpdateInput> {
    /**
     * 复制
     *
     * @param modifyInput
     * @param user
     */
    void copyModify(UserModifyInput modifyInput, @MappingTarget User user);

    void copyModify(UserLoginInferiorUpdateInput userLoginInferiorUpdateInput, @MappingTarget User user);

    List<AccountListDto> accountListToDto(List<Account> accountList);
}
