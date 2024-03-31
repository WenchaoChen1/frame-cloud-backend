package com.frame.template.service.system.service;

import com.frame.template.service.system.pojo.base.user.UserDto;
import com.frame.template.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.frame.template.service.system.pojo.base.user.UserPageQueryCriteria;
import com.frame.template.service.system.pojo.vo.UserInsertInput;
import com.frame.template.service.system.pojo.vo.UserUpdateInput;
import com.frame.template.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.frame.template.common.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
  UserDto create(UserDto userDto,String tenentId);

  Result<UserDto> insertUserInitializationToResult(UserInsertInput userInsertInput);

  List<AccountListDto> getByIdToAccount(String id);
}


