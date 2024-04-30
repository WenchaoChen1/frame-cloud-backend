package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.user.UserPageQueryCriteria;
import com.gstdev.cloud.service.system.pojo.entity.User;
import com.gstdev.cloud.service.system.pojo.vo.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOService;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.user.UserPageQueryCriteria;
import com.gstdev.cloud.service.system.pojo.vo.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;

import java.util.List;

public interface UserService extends BasePOJOService<User, String, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

    //////////////////////////////////////////自定义代码//////////////////////////////////////////////////////////////
    UserDto create(UserDto userDto, String tenentId);

    Result<UserDto> insertUserInitializationToResult(UserInsertInput userInsertInput);

    List<AccountListDto> getByIdToAccount(String id);
}


