package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.UserVoMapper;
import com.frame.template.service.system.pojo.base.user.UserDto;
import com.frame.template.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.frame.template.service.system.pojo.base.user.UserPageQueryCriteria;
import com.frame.template.service.system.pojo.base.user.UserVo;
import com.frame.template.service.system.pojo.vo.UserInsertInput;
import com.frame.template.service.system.pojo.vo.UserUpdateInput;
import com.frame.template.service.system.pojo.vo.user.AccountListDto;
import com.frame.template.service.system.service.UserService;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.BaseController;
import com.frame.template.common.base.BaseRedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.user.*;
import com.frame.template.service.system.service.CommonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController<UserService, UserVoMapper, UserVo, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

  @Resource
  private UserService userService;

  @Resource
  private CommonService commonService;

  @Resource
  private UserVoMapper userVoMapper;

  @Resource
  private BaseRedisCurrentLoginInformation redisCurrentLoginInformation;

  public UserController(UserService userService, UserVoMapper userVoMapper) {
    super(userService, userVoMapper);
    this.userService = userService;
    this.userVoMapper = userVoMapper;
  }

  @GetMapping("/get-all-user")
  @ApiOperation("获取所有的用户")
  public Result<List<UserVo>> getAllQueryCriteria() {
    UserFindAllByQueryCriteria userFindAllByQueryCriteria = new UserFindAllByQueryCriteria();
    return findAllByQueryCriteriaToResult(userFindAllByQueryCriteria);
  }

  @GetMapping("/get-user-page")
  @ApiOperation("获取所有的用户,分页")
  public Result<Page<UserVo>> getAllPageQueryCriteria(UserPageQueryCriteria userPageQueryCriteria, Pageable pageable) {
    return pageToResult(userPageQueryCriteria, pageable);
  }

  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<UserVo> getById(@RequestParam("id")  String id) {
    return findByIdToResult(id);
  }

  @PostMapping
  @ApiOperation("新增一条数据")
  public Result<UserVo> insert(@RequestBody @Validated UserInsertInput userInsertInput) {
    return insertToResult(userInsertInput);
  }
//
//  @PostMapping("/insert-user-initialization")
//  @ApiOperation("新增一个用户并创建用户的账户以及角色,部门")
//  public Result<UserVo> insertUserInitialization(@RequestBody @Validated UserInsertInput userInsertInput) {
//    return getMapper().toVo(getService().insertUserInitializationToResult(userInsertInput));
//  }

  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<UserVo> update(@RequestBody UserUpdateInput userUpdateInput) {
    return updateToResult(userUpdateInput);
  }

  @ApiOperation("删除用户及所属的账号信息")
  @DeleteMapping
  public Result<UserVo> deleteById(@RequestParam("id")  String id) {
    return deleteByIdToResult(id);
  }
  // *********************************访问控制*****************************************

  @GetMapping("/get-by-id-to-account")
  @ApiOperation("根据id获取当前用户下的所有账户")
  public Result<List<AccountListDto>> getByIdToAccount() {
    return Result.success(userService.getByIdToAccount(redisCurrentLoginInformation.getCurrentLoginInformation().getUserId()));
  }
}
