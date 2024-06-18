package com.frame.template.autoconfigure.service.system.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gstdev.cloud.service.system.domain.base.account.AccountDto;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.exception.CommonException;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.service.system.service.SysAccountService;
import com.gstdev.cloud.service.system.service.SysMenuService;
import com.gstdev.cloud.service.system.service.SysTenantService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

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
        AccountDto accountDto = null;
        if (redisCurrentLoginInformationInput.getAccountId() != null) {
            accountDto = accountService.findByIdToDto(redisCurrentLoginInformationInput.getAccountId());
        }
        if (accountDto == null || accountDto.getId() == null) {
            List<AccountDto> accountDtos = accountService.findAllByUserId(SecurityUtils.getUserId());
            if (accountDtos.size() == 0) {
                throw new CommonException("No account was found, please log in again");
            }
            accountDto = accountDtos.get(0);
        }
        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
        currentLoginInformation.setUserId(accountDto.getUserId());
        currentLoginInformation.setAccountId(accountDto.getId());
        currentLoginInformation.setTenantId(accountDto.getTenantId());
        currentLoginInformation.setType(accountDto.getType().getValue());
        currentLoginInformation.setTenant(new JSONObject(tenantService.findByIdToDto(accountDto.getTenantId())));
//    currentLoginInformation.setCurrentLoginAccount(accountDto.toString());
        currentLoginInformation.setCurrentLoginAccount(new JSONObject(accountDto));
        currentLoginInformation.setCurrentLoginAccountUserPermissions(new JSONArray(menuService.getAccountPermissions(accountDto.getId())));
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
