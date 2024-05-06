package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.mapper.vo.UserVoMapper;
import com.gstdev.cloud.service.system.pojo.base.user.UserDto;
import com.gstdev.cloud.service.system.pojo.base.user.UserFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.user.UserPageQueryCriteria;
import com.gstdev.cloud.service.system.pojo.base.user.UserVo;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.vo.UserInsertInput;
import com.gstdev.cloud.service.system.pojo.vo.UserUpdateInput;
import com.gstdev.cloud.service.system.pojo.vo.user.AccountListDto;
import com.gstdev.cloud.service.system.service.UserService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.service.CommonService;
import com.gstdev.cloud.rest.core.controller.POJOController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController implements POJOController<SysUser, String, UserService, UserVoMapper, UserVo, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

    @Resource
    private UserService userService;

    @Resource
    private UserVoMapper userVoMapper;


    @Override
    public UserService getService() {
        return userService;
    }

    @Override
    public UserVoMapper getMapper() {
        return userVoMapper;
    }

    public UserController(UserService userService, CommonService commonService, UserVoMapper userVoMapper) {
        this.userService = userService;
        this.userVoMapper = userVoMapper;
    }

    @GetMapping("/get-all-user")
    @Operation(summary = "获取所有的用户")
    public Result<List<UserVo>> getAllQueryCriteria() {
        UserFindAllByQueryCriteria userFindAllByQueryCriteria = new UserFindAllByQueryCriteria();
        return findAllByQueryCriteriaToResult(userFindAllByQueryCriteria);
    }

    @GetMapping("/get-user-page")
    @Operation(summary = "获取所有的用户,分页")
    public Result<Page<UserVo>> getAllPageQueryCriteria(UserPageQueryCriteria userPageQueryCriteria, Pageable pageable) {
        return pageToResult(userPageQueryCriteria, pageable);
    }

    @GetMapping("/page")
    @Operation(summary = "获取所有的用户,分页")
    public Result<Page<UserVo>> a(UserPageQueryCriteria userPageQueryCriteria, PageRequest pageable) {
        return null;
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<UserVo> getById(@RequestParam("id") String id) {
        return findByIdToResult(id);
    }

    @GetMapping("/security/sign-in/{username}")
    @Operation(summary = "根据username获取实体数据")
    Result<DefaultSecurityUser> signInFindByUsername(@PathVariable("username") String username) {
        return Result.success(getService().signInFindByUsername(username));
    }
//    @GetMapping("/security/sign-in/{username}")
//    @Operation(summary = "根据username获取实体数据")
//    Result<DefaultSecurityUser> findByUsername(@PathVariable("username") String username) {
//        return Result.success(getService().signInFindByUsername(username));
//    }

    @PostMapping
    @Operation(summary = "新增一条数据")
    public Result<UserVo> insert(@RequestBody @Validated UserInsertInput userInsertInput) {
        return insertToResult(userInsertInput);
    }

    @PostMapping("/insert-user-initialization")
    @Operation(summary = "新增一个用户并创建用户的账户以及角色,部门")
    public Result<UserVo> insertUserInitialization(@RequestBody @Validated UserInsertInput userInsertInput) {
        return getMapper().toVo(getService().insertUserInitializationToResult(userInsertInput));
    }

    @PutMapping
    @Operation(summary = "修改一条数据")
    public Result<UserVo> update(@RequestBody UserUpdateInput userUpdateInput) {
        return updateToResult(userUpdateInput);
    }

    @Operation(summary = "删除用户及所属的账号信息")
    @DeleteMapping
    public Result<UserVo> deleteById(@RequestParam("id") String id) {
        return deleteByIdToResult(id);
    }
    // *********************************访问控制*****************************************

    @GetMapping("/get-by-id-to-account")
    @Operation(summary = "根据id获取当前用户下的所有账户")
    public Result<List<AccountListDto>> getByIdToAccount() {
        return Result.success(userService.getByIdToAccount(SecurityUtils.getUserId()));
    }


}
