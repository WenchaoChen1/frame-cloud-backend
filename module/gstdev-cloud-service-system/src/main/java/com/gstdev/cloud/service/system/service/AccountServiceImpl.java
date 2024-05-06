package com.gstdev.cloud.service.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gstdev.cloud.service.system.mapper.AccountMapper;
import com.gstdev.cloud.service.system.pojo.base.account.*;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.repository.AccountRepository;
import com.gstdev.cloud.service.system.repository.DepartRepository;
import com.gstdev.cloud.service.system.repository.RoleRepository;
import com.gstdev.cloud.service.system.repository.UserRepository;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import org.springframework.util.ObjectUtils;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class AccountServiceImpl implements AccountService {
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl extends BasePOJOServiceImpl<SysAccount, String, AccountRepository, AccountMapper, AccountDto, AccountInsertInput, AccountUpdateInput, AccountPageQueryCriteria, AccountFindAllByQueryCriteria> implements AccountService {

    @Resource
    private AccountRepository accountRepository;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserRepository userRepository;
    @Resource
    private DepartRepository departRepository;
    @Resource
    private RoleRepository roleRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        super(accountRepository, accountMapper);
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    @Transactional
    public SysAccount insertAccountInitialization(AccountInsertInput accountInsertInput) {
        SysAccount account = toEntityInsert(accountInsertInput);
        SysUser user = userRepository.findById(accountInsertInput.getUserId()).get();
        account.setUser(user);
        account.setType(accountInsertInput.getAccountTypeConstants().getCode());
        if (!ObjectUtils.isEmpty(accountInsertInput.getDepartIds()))
            account.setDeparts(departRepository.findAllById(accountInsertInput.getDepartIds()));
        if (!ObjectUtils.isEmpty(accountInsertInput.getRoleIds()))
            account.setRoles(roleRepository.findAllById(accountInsertInput.getRoleIds()));
        SysAccount insert = insert(account);
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
    public SysAccount save(SysAccount var) {
//        var.setUpdatedBy(redisCurrentLoginInformation.getCurrentLoginAccountId());
//        if (redisCurrentLoginInformation.getCurrentLoginAccountId().equals(var.getId()) && !redisCurrentLoginInformation.getCurrentLoginInformation().getAccountType().equals(var.getType())) {
//            throw new PlatformRuntimeException("This action is not available");
//        }
        return getRepository().save(var);
    }

    @Override
    public List<AccountDto> findAllByUserId(String userId) {
        List<SysAccount> allByUserId = accountRepository.findAllByUserId(userId);
        List<AccountDto> accountDtos = getMapper().toDto(allByUserId);
        return accountDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTenantId(String tenantId) {
        List<SysAccount> accountList = accountRepository.findAllByTenantId(tenantId);
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
