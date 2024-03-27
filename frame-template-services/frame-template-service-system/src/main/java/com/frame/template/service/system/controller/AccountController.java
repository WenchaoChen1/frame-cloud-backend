package com.frame.template.service.system.controller;

import com.frame.template.service.system.mapper.vo.AccountVoMapper;
import com.frame.template.service.system.pojo.base.account.*;
import com.gstdev.cloud.commons.domain.Result;
import com.frame.template.common.base.BaseController;
import com.frame.template.common.base.BaseRedisCurrentLoginInformation;
import com.frame.template.service.system.pojo.base.account.*;
import com.frame.template.service.system.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/v1/account")
public class AccountController extends BaseController<AccountService, AccountVoMapper, AccountVo, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> {

  @Resource
  private AccountService accountService;

  @Resource
  private AccountVoMapper accountVoMapper;
  @Resource
  private BaseRedisCurrentLoginInformation redisCurrentLoginInformation;
  public AccountController(AccountService accountService, AccountVoMapper accountVoMapper) {
    super(accountService, accountVoMapper);
    this.accountService = accountService;
    this.accountVoMapper = accountVoMapper;
  }
//  @GetMapping("/get-all-account")
//  @ApiOperation("获取所有的账户")
//  public Result<List<AccountVo>> getAllQueryCriteria(@RequestParam("tenantId") String tenantId) {
//    AccountFindAllByQueryCriteria accountFindAllByQueryCriteria=new AccountFindAllByQueryCriteria();
//    accountFindAllByQueryCriteria.setTenantId(tenantId);
//    return findAllByQueryCriteriaToResult(accountFindAllByQueryCriteria);
//  }
  @GetMapping("/get-account-page")
  @ApiOperation("获取所有的账户,分页")
  public Result<Page<AccountVo>> getAllPageQueryCriteria(AccountPageQueryCriteria accountPageQueryCriteria, Pageable pageable) {
    return pageToResult(accountPageQueryCriteria, pageable);
  }
  @GetMapping("/get-by-id")
  @ApiOperation("根据id获取实体数据")
  public Result<AccountVo> getById(@RequestParam("id")  String id) {
    return findByIdToResult(id);
  }

//  @PostMapping
//  @ApiOperation("新增一条数据")
//  public Result<AccountVo> insert(@RequestBody @Validated AccountInsertInput accountInsertInput) {
//    return insertToResult(accountInsertInput);
//  }
//
  @PutMapping
  @ApiOperation("修改一条数据")
  public Result<AccountVo> update(@RequestBody @Validated AccountUpdateInput updateInput) {
    return updateToResult(updateInput);
  }
  @ApiOperation("删除一条数据")
  @DeleteMapping
  public Result<AccountVo> deleteById(String id) {
    return deleteByIdToResult(id);
  }

  @PostMapping("/insert-account-initialization")
  @ApiOperation("新增一个账户并创建角色,部门")
  public Result<AccountVo> insertAccountInitialization(@RequestBody @Validated AccountInsertInput accountInsertInput) {
    return getMapper().toVo(getService().insertAccountInitializationToResult(accountInsertInput));
  }

  // *********************************访问控制*******************************************

}
