package com.frame.template.autoconfigure.service.system.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.frame.template.common.exception.CommonException;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.domain.entity.SysAccount;
import com.gstdev.cloud.service.system.service.SysAccountService;
import com.gstdev.cloud.service.system.service.SysMenuService;
import com.gstdev.cloud.service.system.service.SysTenantService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RedisCurrentLoginInformationServiceImpl implements RedisCurrentLoginInformationService {


    @Resource
    private RedisCurrentLoginInformation redisCurrentLoginInformation;

    @Resource
    private SysAccountService accountService;
    @Resource
    private SysMenuService menuService;
    @Resource
    private SysTenantService tenantService;

    @Override
    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        SysAccount account = null;
        if (!ObjectUtils.isEmpty(redisCurrentLoginInformationInput.getAccountId())) {
            account = accountService.findById(redisCurrentLoginInformationInput.getAccountId());
        }
        if (account == null || account.getId() == null) {
            List<SysAccount> accounts = accountService.findAllByUserId(SecurityUtils.getUserId());
            if (ObjectUtils.isEmpty(accounts)) {
                throw new CommonException("No account was found, please log in again");
            }
            account = accounts.get(0);
        }
        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
        currentLoginInformation.setUserId(account.getUser().getId());
        currentLoginInformation.setUserName(account.getUser().getUsername());
        currentLoginInformation.setAccountId(account.getId());
        currentLoginInformation.setAccountName(account.getName());
        currentLoginInformation.setTenantId(account.getTenantId());
        currentLoginInformation.setType(account.getType().getValue());
        currentLoginInformation.setTenant(new JSONObject(tenantService.findByIdToDto(account.getTenantId())));
//        currentLoginInformation.setCurrentLoginAccount(new JSONObject(account));
        currentLoginInformation.setCurrentLoginAccountUserPermissions(new JSONArray(menuService.getAccountMenuPermissions(account.getId())));
        redisCurrentLoginInformation.addByTokenCurrentLoginInformation(currentLoginInformation);
        return Result.success(currentLoginInformation);
    }

    @Override
    public Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
        currentLoginInformation.setAccountId(redisCurrentLoginInformationInput.getAccountId());
        redisCurrentLoginInformation.updateByTokenCurrentLoginInformation(currentLoginInformation);
        return Result.success();
    }

    @Override
    public Result<Object> deleteByTokenCurrentLoginInformation() {
        redisCurrentLoginInformation.deleteByTokenCurrentLoginInformation();
        return Result.success();
    }

    @Override
    public Result<CurrentLoginInformation> getCurrentLoginInformation() {
        CurrentLoginInformation currentLoginInformation = redisCurrentLoginInformation.getCurrentLoginInformation();
        return Result.success(currentLoginInformation);
    }


}
