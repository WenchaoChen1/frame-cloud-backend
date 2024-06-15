package com.gstdev.cloud.service.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gstdev.cloud.data.core.pojo.BaseDto;
import com.gstdev.cloud.data.core.service.BaseDtoServiceImpl;
import com.gstdev.cloud.service.system.mapper.vo.SysAccountMapper;
import com.gstdev.cloud.service.system.pojo.base.account.*;
import com.gstdev.cloud.service.system.pojo.entity.SysAccount;
import com.gstdev.cloud.service.system.pojo.entity.SysUser;
import com.gstdev.cloud.service.system.pojo.o.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.repository.SysAccountRepository;
import com.gstdev.cloud.service.system.repository.SysDepartRepository;
import com.gstdev.cloud.service.system.repository.SysRoleRepository;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import com.gstdev.cloud.data.core.service.BasePOJOServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Transactional(readOnly = true)
//public  class SysAccountServiceImpl extends BasePOJOServiceImpl<SysAccount, String, SysAccountRepository, SysAccountMapper, AccountDto> implements SysAccountService {
public  class SysAccountServiceImpl extends BaseDtoServiceImpl<SysAccount, String, SysAccountRepository, SysAccountMapper, AccountDto> implements SysAccountService {

    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysDepartRepository departRepository;
    @Resource
    private SysRoleRepository roleRepository;

    @Resource
    private SysAccountRepository accountRepository;

    public SysAccountServiceImpl(SysAccountRepository accountRepository, SysAccountMapper accountMapper) {
        super(accountRepository, accountMapper);
    }
    public SysAccountRepository getRepository() {
        return accountRepository;
    }



    @Override
    @Transactional
    public SysAccount insertAccountManageInitialization(InsertAccountManageInitializationIO accountInsertInput) {
        SysAccount account=new SysAccount();
        account.setType(accountInsertInput.getType());
        account.setTenantId(accountInsertInput.getTenantId());
        account.setName(accountInsertInput.getName());
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
    public AccountDto insertAccountManageInitializationToDto(InsertAccountManageInitializationIO accountInsertInput) {
        return getMapper().toDto(insertAccountManageInitialization(accountInsertInput));
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
        List<SysAccount> allByUserId = getRepository().findAllByUserId(userId);
        List<AccountDto> accountDtos = getMapper().toDto(allByUserId);
        return accountDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTenantId(String tenantId) {
        List<SysAccount> accountList = getRepository().findAllByTenantId(tenantId);
        if (ObjectUtil.isNotEmpty(accountList)) {
            getRepository().deleteAll(accountList);
        }
    }
//  @Override
//  public Integer findByTenantId(String tenantId) {
//    List<Account> accounts = accountRepository.findAllByTenantId(tenantId);
//      return accounts.size();
//  }


}
