package com.gstdev.cloud.service.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.base.account.AccountDto;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.domain.entity.SysUser;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.InsertAccountManageInitializationIO;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.SwitchUserAccountDetailDto;
import com.gstdev.cloud.service.system.domain.pojo.sysAccount.UpdateAccountManageIO;
import com.gstdev.cloud.service.system.mapper.SysAccountMapper;
import com.gstdev.cloud.service.system.repository.SysAccountRepository;
import com.gstdev.cloud.service.system.repository.SysDepartRepository;
import com.gstdev.cloud.service.system.repository.SysRoleRepository;
import com.gstdev.cloud.service.system.repository.SysUserRepository;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Transactional(readOnly = true)
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccount, String, SysAccountRepository> implements SysAccountService {

    @Resource
    private SysUserRepository userRepository;
    @Resource
    private SysDepartRepository departRepository;
    @Resource
    private SysRoleRepository roleRepository;
    @Resource
    @Getter
    private SysAccountMapper accountMapper;
    @Resource
    private SysAccountRepository accountRepository;

    public SysAccountServiceImpl(SysAccountRepository accountRepository, SysAccountMapper accountMapper) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public SysAccountRepository getRepository() {
        return accountRepository;
    }


    @Override
    @Transactional
    public SysAccount insertAccountManageInitialization(InsertAccountManageInitializationIO accountInsertInput) {
        SysAccount account = new SysAccount();
        account.setType(accountInsertInput.getType());
        account.setTenantId(accountInsertInput.getTenantId());
        account.setName(accountInsertInput.getName());
        SysUser user = userRepository.findById(accountInsertInput.getUserId()).get();
        account.setUser(user);
        account.setType(accountInsertInput.getType());
        if (!ObjectUtils.isEmpty(accountInsertInput.getDepartIds())) {
            account.setDeparts(departRepository.findAllById(accountInsertInput.getDepartIds()));
        }
        if (!ObjectUtils.isEmpty(accountInsertInput.getRoleIds())) {
            account.setRoles(roleRepository.findAllById(accountInsertInput.getRoleIds()));
        }
        SysAccount insert = insert(account);
        return insert;
    }

    @Override
    @Transactional
    public AccountDto insertAccountManageInitializationToDto(InsertAccountManageInitializationIO accountInsertInput) {
        return accountMapper.toDto(insertAccountManageInitialization(accountInsertInput));
    }

    @Override
    @Transactional
    public SysAccount save(SysAccount var) {
        return getRepository().save(var);
    }

    @Override
    public List<SysAccount> findAllByUserId(String userId) {
        return getRepository().findAllByUserUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTenantId(String tenantId) {
        List<SysAccount> accountList = getRepository().findAllByTenantId(tenantId);
        if (ObjectUtil.isNotEmpty(accountList)) {
            getRepository().deleteAll(accountList);
        }
    }

    @Override
    @Transactional
    public void insertAccountManage(InsertAccountManageIO insertAccountManageIO) {
        SysAccount entity = accountMapper.toEntity(insertAccountManageIO);
        entity.setUser(userRepository.findById(insertAccountManageIO.getUserId()).get());
        insertAndUpdate(entity);
    }

    @Override
    @Transactional
    public void updateAccountManage(UpdateAccountManageIO updateAccountManageIO) {
        SysAccount entity = findById(updateAccountManageIO.getAccountId());
        accountMapper.copy(updateAccountManageIO, entity);
        entity.setUser(userRepository.findById(updateAccountManageIO.getUserId()).get());
        insertAndUpdate(entity);
    }

    public List<SwitchUserAccountDetailDto> getSwitchUserAccountDetail() {
        return accountMapper.toSwitchUserAccountDetailDto(findAllByUserId(SecurityUtils.getUserId()));
    }


}
