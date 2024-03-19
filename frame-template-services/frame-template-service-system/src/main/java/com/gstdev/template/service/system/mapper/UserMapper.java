package com.gstdev.template.service.system.mapper;

import com.gstdev.template.common.base.BaseMapper;
import com.gstdev.template.service.system.pojo.domain.Account;
import com.gstdev.template.service.system.pojo.domain.User;
import com.gstdev.template.service.system.pojo.base.user.UserDto;
import com.gstdev.template.service.system.pojo.vo.UserInsertInput;
import com.gstdev.template.service.system.pojo.base.user.UserLoginInferiorUpdateInput;
import com.gstdev.template.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.template.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.template.service.system.pojo.vo.user.UserModifyInput;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper extends BaseMapper<User, UserDto, UserInsertInput, UserUpdateInput> {
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
