package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.rest.core.controller.ResultController;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.*;
import com.gstdev.cloud.service.system.mapper.SysAccountMapper;
import com.gstdev.cloud.service.system.service.SysAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/account")
public class SysAccountController implements ResultController {

    @Resource
    private SysAccountService accountService;

    @Resource
    private SysAccountMapper accountVoMapper;

    public SysAccountService getService() {
        return accountService;
    }

    public SysAccountMapper getMapper() {
        return accountVoMapper;
    }

    // ********************************* Account Manage *****************************************
    @Tag(name = "demo Manage")
    @GetMapping("/get-demo")
    @Operation(summary = "get-demo")
    public Result getDemo() {
        PrincipalDetails principal = SecurityUtils.getPrincipal();
        return this.result(principal);
    }

    @Tag(name = "Account Manage")
    @GetMapping("/get-account-manage-page")
    @Operation(summary = "get-account-manage-page")
    public Result<Map<String, Object>> getAccountManagePage(AccountManageQO accountManageQO, BasePage basePage) {
        Page<SysAccount> byPage = this.getService().findByPage((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, accountManageQO, criteriaBuilder), basePage);
        return this.result(this.getMapper().toAccountManagePageVo(byPage));
    }

    @Tag(name = "Account Manage")
    @GetMapping("/get-account-manage-detail/{id}")
    @Operation(summary = "get-account-manage-detail")
    public Result<AccountManageDetailVo> getAccountManageDetail(@PathVariable String id) {
        return result(accountVoMapper.toAccountManageDetailVo(getService().findById(id)));
    }

    @Tag(name = "Account Manage")
    @PostMapping("/insert-account-manage")
    @Operation(summary = "insert-account-manage")
    public Result<String> insertAccountManage(@RequestBody @Validated InsertAccountManageIO insertAccountManageIO) {
        this.getService().insertAccountManage(insertAccountManageIO);
        return Result.success();
    }

    @Tag(name = "Account Manage")
    @PutMapping("/update-account-manage")
    @Operation(summary = "update-account-manage")
    public Result<String> updateAccountManage(@RequestBody @Validated UpdateAccountManageIO updateAccountManageIO) {
        this.getService().updateAccountManage(updateAccountManageIO);
        return Result.success();
    }

    @Tag(name = "Account Manage")
    @PostMapping("/insert-account-manage-initialization")
    @Operation(summary = "insert-account-manage-initialization")
    public Result<String> insertAccountManageInitialization(@RequestBody @Validated InsertAccountManageInitializationIO userInsertInput) {
        getService().insertAccountManageInitializationToDto(userInsertInput);
        return Result.success();
    }

    @Tag(name = "Account Manage")
    @DeleteMapping("/delete-account-manage/{accountId}")
    @Operation(summary = "delete-account-manage")
    public Result<String> deleteAccountManage(@PathVariable String accountId) {
        getService().deleteById(accountId);
        return Result.success();
    }

    @Tag(name = "Account Manage")
    @Operation(summary = "delete-all-account-manage")
    @DeleteMapping("/delete-all-account-manage")
    public Result<String> deleteAllAccountManage(List<String> accountIds) {
        getService().deleteAllById(accountIds);
        return Result.success();
    }

    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/
//    获取可切换账户的信息
    @Tag(name = "Switch Account")
    @GetMapping("/get-switch-user-account-detail")
    @Operation(summary = "get-switch-user-account-detail")
    public Result<List<SwitchUserAccountDetailVo>> getSwitchUserAccountDetail() {
        return this.result(this.getMapper().toSwitchUserAccountDetailVo(this.getService().getSwitchUserAccountDetail()));
    }

}
