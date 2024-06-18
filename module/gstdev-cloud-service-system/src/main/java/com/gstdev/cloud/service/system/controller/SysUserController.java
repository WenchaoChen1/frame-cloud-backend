package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.rest.core.controller.POJOController;
import com.gstdev.cloud.service.system.domain.base.user.UserDto;
import com.gstdev.cloud.service.system.domain.base.user.UserFindAllByQueryCriteria;
import com.gstdev.cloud.service.system.domain.base.user.UserPageQueryCriteria;
import com.gstdev.cloud.service.system.domain.base.user.UserVo;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.InsertUserManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UpdateUserManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.UserManageQO;
import com.gstdev.cloud.service.system.domain.vo.user.UserInsertInput;
import com.gstdev.cloud.service.system.domain.vo.user.UserUpdateInput;
import com.gstdev.cloud.service.system.mapper.vo.SysUserMapper;
import com.gstdev.cloud.service.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
//@ResponseBody
@RestController
@RequestMapping("/v1/user")
public class SysUserController implements POJOController<SysUser, String, UserVo, UserDto, UserInsertInput, UserUpdateInput, UserPageQueryCriteria, UserFindAllByQueryCriteria> {

    @Resource
    private SysUserService userService;

    @Resource
    private SysUserMapper userVoMapper;

    @Override
    public SysUserService getService() {
        return userService;
    }

    @Override
    public SysUserMapper getMapper() {
        return userVoMapper;
    }


    // ********************************* User Manage *****************************************

    @GetMapping("/get-user-manage-page")
    @Operation(summary = "获取所有的用户,分页")
    public Result<Map<String, Object>> getUserManagePage(UserManageQO sysUserUserManageQO, Pageable pageable) {
        return findByPageToVo((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, sysUserUserManageQO, criteriaBuilder), pageable);
    }

    @GetMapping("/get-user-manage-detail/{id}")
    @Operation(summary = "get-user-manage-detail")
    public Result<UserVo> getUserManageDetail(@PathVariable String id) {
        return findByIdToResult(id);
    }

    @PostMapping("/insert-user-manage")
    @Operation(summary = "新增一条数据")
    public Result insertUserManage(@RequestBody @Validated InsertUserManageIO insertUserManageIO) {
        this.getService().insertAndUpdate(userVoMapper.toEntity(insertUserManageIO));
        return result();
    }

    @PutMapping("/update-user-manage")
    @Operation(summary = "新增一条数据")
    public Result updateUserManage(@RequestBody @Validated UpdateUserManageIO updateUserManageIO) {
        SysUser sysUser = this.getService().findById(updateUserManageIO.getId());
        userVoMapper.copy(updateUserManageIO, sysUser);
        this.getService().insertAndUpdate(sysUser);
        return result();
    }

    @PostMapping("/insert-user-manage-initialization")
    @Operation(summary = "新增一个用户并创建用户的账户以及角色,部门")
    public Result insertUserManageInitialization(@RequestBody @Validated InsertUserManageInitializationIO userInsertInput) {
        getService().insertUserManageInitialization(userInsertInput);
        return result();
    }


    @Operation(summary = "删除一条数据")
    @DeleteMapping("delete-user-manage/{id}")
    public Result deleteUserManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Operation(summary = "删除多条数据")
    @DeleteMapping("delete-all-user-manage")
    public Result deleteAllUserManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }

    // ********************************* 登录 *****************************************

    @GetMapping("/security/sign-in/{username}")
    @Operation(summary = "根据username获取实体数据")
    public Result<DefaultSecurityUser> signInFindByUsername(@PathVariable("username") String username) {
        return Result.success(getService().signInFindByUsername(username));
    }

    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/

    //    public SysUserController(SysUserService userService, UserVoMapper userVoMapper) {
//        this.userService = userService;
//        this.userVoMapper = userVoMapper;
//    }

//    @GetMapping("/get-all-user")
//    @Operation(summary = "获取所有的用户")
//    public Result<List<UserVo>> getAllQueryCriteria() {
//        UserFindAllByQueryCriteria userFindAllByQueryCriteria = new UserFindAllByQueryCriteria();
//        return findAllByQueryCriteriaToResult(userFindAllByQueryCriteria);
//    }
//
//    @GetMapping("/get-user-page")
//    @Operation(summary = "获取所有的用户,分页")
//    public Result<Page<UserVo>> getAllPageQueryCriteria(UserPageQueryCriteria userPageQueryCriteria, Pageable pageable) {
//        return pageToResult(userPageQueryCriteria, pageable);
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获取所有的用户,分页")
//    public Result<Page<UserVo>> a(UserPageQueryCriteria userPageQueryCriteria, PageRequest pageable) {
//        return pageToResult(userPageQueryCriteria, pageable);
//    }
//
//    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<UserVo> getById(@RequestParam("id") String id) {
//        return findByIdToResult(id);
//    }

//    @PostMapping
//    @Operation(summary = "新增一条数据")
//    public Result<UserVo> insert(@RequestBody @Validated UserInsertInput userInsertInput) {
//        return insertToResult(userInsertInput);
//    }
//
//    @PostMapping("/insert-user-initialization")
//    @Operation(summary = "新增一个用户并创建用户的账户以及角色,部门")
//    public Result<UserVo> insertUserInitialization(@RequestBody @Validated UserInsertInput userInsertInput) {
//        return result(getMapper().toVo(getService().insertUserInitializationToDto(toEntityInsert(userInsertInput),userInsertInput)));
//    }
//
//    @PutMapping
//    @Operation(summary = "修改一条数据")
//    public Result<UserVo> update(@RequestBody UserUpdateInput userUpdateInput) {
//        return updateToResult(userUpdateInput);
//    }

//    @Operation(summary = "删除用户及所属的账号信息")
//    @DeleteMapping
//    public Result<UserVo> deleteById(@RequestParam("id") String id) {
//        return deleteByIdToResult(id);
//    }
//


//    @GetMapping("/get-by-id-to-account")
//    @Operation(summary = "根据id获取当前用户下的所有账户")
//    public Result<List<AccountListDto>> getByIdToAccount() {
//        return Result.success(userService.getByIdToAccount(SecurityUtils.getUserId()));
//    }


}
