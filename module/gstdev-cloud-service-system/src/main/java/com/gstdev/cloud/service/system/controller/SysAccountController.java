package com.gstdev.cloud.service.system.controller;

import com.gstdev.cloud.data.core.utils.QueryUtils;
import com.gstdev.cloud.service.system.mapper.vo.AccountMapper;
import com.gstdev.cloud.service.system.pojo.base.account.*;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.AccountManageQO;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAndUpdateAccountManageIO;
import com.gstdev.cloud.service.system.service.SysAccountService;
import com.gstdev.cloud.rest.core.controller.POJOController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.Map;

//@ResponseBody
@RestController
@RequestMapping("/v1/account")
public class SysAccountController implements POJOController<SysAccount, String, AccountVo, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {

    @Resource
    private SysAccountService accountService;

    @Resource
    private AccountMapper accountVoMapper;

    @Override
    public SysAccountService getService() {
        return accountService;
    }

    @Override
    public AccountMapper getMapper() {
        return accountVoMapper;
    }

//    @GetMapping("/get-account-page")
//    @Operation(summary = "获取所有的账户,分页")
//    public Result<Page<AccountVo>> getAllPageQueryCriteria(AccountPageQueryCriteria accountPageQueryCriteria, Pageable pageable) {
//        return pageToResult(accountPageQueryCriteria, pageable);
//    }

    @GetMapping("/get-by-id")
    @Operation(summary = "根据id获取实体数据")
    public Result<AccountVo> getById(@RequestParam("id") String id) {
        return findByIdToResult(id);
    }
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

    @Operation(summary = "删除一条数据")
    @DeleteMapping
    public Result<AccountVo> deleteById(String id) {
        return deleteByIdToResult(id);
    }
//
//    @PostMapping("/insert-account-initialization")
//    @Operation(summary = "新增一个账户并创建角色,部门")
//    public Result<AccountVo> insertAccountInitialization(@RequestBody @Validated AccountInsertInput accountInsertInput) {
//        return getMapper().toVo(getService().insertAccountInitializationToResult(accountInsertInput));
//    }

    // *********************************访问控制*******************************************
    // ********************************* Account Manage *****************************************
    @GetMapping("/get-account-manage-page")
    @Operation(summary = "获取所有的用户,分页")
    public Result<Map<String, Object>> getAccountManagePage(AccountManageQO accountManageQO, Pageable pageable) {
        return findByPageToVo((root, criteriaQuery, criteriaBuilder) -> {
            return QueryUtils.getPredicate(root, accountManageQO, criteriaBuilder);
        }, pageable);
    }

    @PostMapping("/insert-and-update-account-manage")
    @Operation(summary = "新增一条数据")
    public Result insertAndUpdateAccountManage(@RequestBody @Validated InsertAndUpdateAccountManageIO insertAndUpdateAccountManageIO) {
        SysAccount sysAccount = new SysAccount();
        if (!ObjectUtils.isEmpty(insertAndUpdateAccountManageIO.getId())) {
            sysAccount = this.getService().findById(insertAndUpdateAccountManageIO.getId());
        }
        accountVoMapper.copy(insertAndUpdateAccountManageIO, sysAccount);
        this.getService().insertAndUpdate(sysAccount);
        return result();
    }
    @PostMapping("/insert-account-manage-initialization")
    @Operation(summary = "新增一个账户并创建角色,部门")
    public Result insertAccountManageInitialization(@RequestBody @Validated InsertAccountManageInitializationIO userInsertInput) {
        getService().insertAccountManageInitializationToDto(userInsertInput);
        return result();
    }

}
