package com.frame.template.service.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.frame.template.service.system.mapper.AccountMapper;
import com.frame.template.service.system.pojo.base.account.*;
import com.frame.template.service.system.pojo.entity.Account;
import com.frame.template.service.system.pojo.entity.User;
import com.frame.template.service.system.repository.AccountRepository;
import com.frame.template.service.system.repository.DepartRepository;
import com.frame.template.service.system.repository.RoleRepository;
import com.frame.template.service.system.repository.UserRepository;
import com.gstdev.cloud.base.definition.exception.PlatformRuntimeException;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class AccountServiceImpl implements AccountService {
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl extends BasePOJOServiceImpl<Account, String, AccountRepository, AccountMapper, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> implements AccountService {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private RedisCurrentLoginInformation redisCurrentLoginInformation;
    @Resource
    private UserRepository userRepository;
    @Resource
    private DepartRepository departRepository;
    @Resource
    private RoleRepository roleRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, RedisCurrentLoginInformation redisCurrentLoginInformation) {
        super(accountRepository, accountMapper);
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.redisCurrentLoginInformation = redisCurrentLoginInformation;
    }


    @Override
    @Transactional
    public Account insertAccountInitialization(AccountInsertInput accountInsertInput) {
        Account account = toEntityInsert(accountInsertInput);
        User user = userRepository.findById(accountInsertInput.getUserId()).get();
        account.setUser(user);
        account.setType(accountInsertInput.getAccountTypeConstants().getCode());
        if (!CollectionUtils.isEmpty(accountInsertInput.getDepartIds()))
            account.setDeparts(departRepository.findAllById(accountInsertInput.getDepartIds()));
        if (!CollectionUtils.isEmpty(accountInsertInput.getRoleIds()))
            account.setRoles(roleRepository.findAllById(accountInsertInput.getRoleIds()));
        Account insert = insert(account);
        return insert;
    }

    @Transactional
    public AccountDto insertAccountInitializationToDto(AccountInsertInput accountInsertInput) {
        return getMapper().toDto(insertAccountInitialization(accountInsertInput));
    }

    @Override
    @Transactional
    public Result<AccountDto> insertAccountInitializationToResult(AccountInsertInput accountInsertInput) {
        return Result.success(insertAccountInitializationToDto(accountInsertInput));
    }

    @Transactional()
    public Account save(Account var) {
        var.setUpdatedBy(redisCurrentLoginInformation.getCurrentLoginAccountId());
        if (redisCurrentLoginInformation.getCurrentLoginAccountId().equals(var.getId()) && !redisCurrentLoginInformation.getCurrentLoginInformation().getAccountType().equals(var.getType())) {
            throw new PlatformRuntimeException("This action is not available");
        }
        return getRepository().save(var);
    }

    @Override
    public List<AccountDto> findAllByUserId(String userId) {
        List<Account> allByUserId = accountRepository.findAllByUserId(userId);
        List<AccountDto> accountDtos = getMapper().toDto(allByUserId);
        return accountDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTenantId(String tenantId) {
        List<Account> accountList = accountRepository.findAllByTenantId(tenantId);
        if (ObjectUtil.isNotEmpty(accountList)) {
            accountRepository.deleteAll(accountList);
        }
    }
//  @Override
//  public Integer findByTenantId(String tenantId) {
//    List<Account> accounts = accountRepository.findAllByTenantId(tenantId);
//      return accounts.size();
//  }


}
