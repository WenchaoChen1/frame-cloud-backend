package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysUser.*;
import com.gstdev.cloud.service.system.mapper.vo.SysUserMapper;
import com.gstdev.cloud.service.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class SysUserController implements ResultController {

    @Resource
    private SysUserService userService;

    @Resource
    private SysUserMapper userVoMapper;

    public SysUserService getService() {
        return userService;
    }

    public SysUserMapper getMapper() {
        return userVoMapper;
    }


    // ********************************* User Manage *****************************************

    @Tag(name = "User Manage")
    @GetMapping("/get-user-manage-page")
    @Operation(summary = "get-user-manage-page")
    public Result<Map<String, Object>> getUserManagePage(UserManageQO sysUserUserManageQO, Pageable pageable) {
        return this.result(this.getMapper().toUserManagePageVo(this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, sysUserUserManageQO, criteriaBuilder), pageable)));
    }

    @Tag(name = "User Manage")
    @GetMapping("/get-user-manage-detail/{id}")
    @Operation(summary = "get-user-manage-detail")
    public Result<UserManageDetailVo> getUserManageDetail(@NotBlank @PathVariable String id) {
        return result(getMapper().toUserManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "User Manage")
    @PostMapping("/insert-user-manage")
    @Operation(summary = "insert-user-manage")
    public Result insertUserManage(@RequestBody @Validated InsertUserManageIO insertUserManageIO) {
        this.getService().insertAndUpdate(userVoMapper.toEntity(insertUserManageIO));
        return result();
    }

    @Tag(name = "User Manage")
    @PutMapping("/update-user-manage")
    @Operation(summary = "update-user-manage")
    public Result updateUserManage(@RequestBody @Validated UpdateUserManageIO updateUserManageIO) {
        SysUser sysUser = this.getService().findById(updateUserManageIO.getId());
        userVoMapper.copy(updateUserManageIO, sysUser);
        this.getService().insertAndUpdate(sysUser);
        return result();
    }

    @Tag(name = "User Manage")
    @PostMapping("/insert-user-manage-initialization")
    @Operation(summary = "insert-user-manage-initialization")
    public Result insertUserManageInitialization(@RequestBody @Validated InsertUserManageInitializationIO userInsertInput) {
        getService().insertUserManageInitialization(userInsertInput);
        return result();
    }

    @Tag(name = "User Manage")
    @Operation(summary = "delete-user-manage")
    @DeleteMapping("/delete-user-manage/{id}")
    public Result deleteUserManage(@PathVariable String id) {
        getService().deleteById(id);
        return result();
    }

    @Tag(name = "User Manage")
    @Operation(summary = "/delete-all-user-manage")
    @DeleteMapping("/delete-all-user-manage")
    public Result deleteAllUserManage(List<String> id) {
        getService().deleteAllById(id);
        return result();
    }

    // ********************************* 登录 *****************************************

    @Tag(name = "security sign-in")
    @GetMapping("/security/sign-in/{username}")
    @Operation(summary = "根据username获取实体数据")
    public Result<DefaultSecurityUser> signInFindByUsername(@NotBlank @PathVariable("username") String username) {
        return Result.success(getService().signInFindByUsername(username));
    }

    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/

}
