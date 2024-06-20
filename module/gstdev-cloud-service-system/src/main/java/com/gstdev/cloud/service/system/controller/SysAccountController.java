package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.utils.BasePage;
import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.rest.core.controller.POJOController;
import com.gstdev.cloud.service.system.domain.base.account.*;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.*;
import com.gstdev.cloud.service.system.mapper.vo.SysAccountMapper;
import com.gstdev.cloud.service.system.service.SysAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@ResponseBody
@RestController
@RequestMapping("/v1/account")
public class SysAccountController implements POJOController<SysAccount, String, AccountVo, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {

    @Resource
    private SysAccountService accountService;

    @Resource
    private SysAccountMapper accountVoMapper;

    @Override
    public SysAccountService getService() {
        return accountService;
    }

    @Override
    public SysAccountMapper getMapper() {
        return accountVoMapper;
    }

    // ********************************* Account Manage *****************************************

    @Tag(name = "Account Manage")
    @GetMapping("/get-account-manage-page")
    @Operation(summary = "获取所有的用户,分页")
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
    @Operation(summary = "新增一条数据")
    public Result insertAccountManage(@RequestBody @Validated InsertAccountManageIO insertAccountManageIO) {

        this.getService().insertAccountManage(insertAccountManageIO);
        return result();
    }

    @Tag(name = "Account Manage")
    @PutMapping("/update-account-manage")
    @Operation(summary = "新增一条数据")
    public Result updateAccountManage(@RequestBody @Validated UpdateAccountManageIO updateAccountManageIO) {

        this.getService().updateAccountManage(updateAccountManageIO);
        return result();
    }

    @Tag(name = "Account Manage")
    @PostMapping("/insert-account-manage-initialization")
    @Operation(summary = "新增一个账户并创建角色,部门")
    public Result insertAccountManageInitialization(@RequestBody @Validated InsertAccountManageInitializationIO userInsertInput) {
        getService().insertAccountManageInitializationToDto(userInsertInput);
        return result();
    }

    @Tag(name = "Account Manage")
    @Operation(summary = "删除一条数据")
    @DeleteMapping("/delete-account-manage/{id}")
    public Result deleteAccountManage(@PathVariable String id) {
        return deleteByIdToResult(id);
    }

    @Tag(name = "Account Manage")
    @Operation(summary = "删除多条数据")
    @DeleteMapping("/delete-all-account-manage")
    public Result deleteAllAccountManage(List<String> id) {
        return deleteAllByIdToResult(id);
    }


    /*------------------------------------------ 以上是系统访问控制 --------------------------------------------*/



    //    @GetMapping("/get-account-page")
//    @Operation(summary = "获取所有的账户,分页")
//    public Result<Page<AccountVo>> getAllPageQueryCriteria(AccountPageQueryCriteria accountPageQueryCriteria, Pageable pageable) {
//        return pageToResult(accountPageQueryCriteria, pageable);
//    }

//    @GetMapping("/get-by-id")
//    @Operation(summary = "根据id获取实体数据")
//    public Result<AccountVo> getById(@RequestParam("id") String id) {
//        return findByIdToResult(id);
//    }
//
//    @PostMapping
//    @Operation(summary = "新增一条数据")
//    public Result<AccountVo> insert(@RequestBody @Validated AccountInsertInput accountInsertInput) {
//        return insertToResult(accountInsertInput);
//    }
//
//    @PutMapping
//    @Operation(summary = "修改一条数据")
//    public Result<AccountVo> update(@RequestBody @Validated AccountUpdateInput updateInput) {
//        return updateToResult(updateInput);
//    }

//    @Operation(summary = "删除一条数据")
//    @DeleteMapping
//    public Result<AccountVo> deleteById(String id) {
//        return deleteByIdToResult(id);
//    }
//
//    @PostMapping("/insert-account-initialization")
//    @Operation(summary = "新增一个账户并创建角色,部门")
//    public Result<AccountVo> insertAccountInitialization(@RequestBody @Validated AccountInsertInput accountInsertInput) {
//        return getMapper().toVo(getService().insertAccountInitializationToResult(accountInsertInput));
//    }

}
