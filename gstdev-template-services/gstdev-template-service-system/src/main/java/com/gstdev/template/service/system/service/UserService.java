package com.gstdev.template.service.system.service;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.base.BaseService;
import com.gstdev.template.service.system.pojo.base.user.UserDto;
import com.gstdev.template.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.gstdev.template.service.system.pojo.base.user.UserPageQueryCriteria;
import com.gstdev.template.service.system.pojo.base.user.UserVo;
import com.gstdev.template.service.system.pojo.vo.UserInsertInput;
import com.gstdev.template.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.template.service.system.pojo.vo.user.AccountListDto;

import java.util.List;

public interface UserService extends BaseService<UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

  //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
  UserDto create(UserDto userDto,String tenentId);

  Result<UserDto> insertUserInitializationToResult(UserInsertInput userInsertInput);

  List<AccountListDto> getByIdToAccount(String id);
}


