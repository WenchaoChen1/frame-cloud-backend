package com.frame.template.service.system.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.frame.template.service.system.pojo.base.account.AccountDto;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.exception.CommonException;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import com.frame.template.service.system.feign.IdentityFeignClient;
import com.gstdev.cloud.base.definition.domain.oauth2.PrincipalDetails;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.utils.PrincipalUtils;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.List;

@Service
public class RedisCurrentLoginInformationServiceImpl implements RedisCurrentLoginInformationService {


    @Autowired
    private RedisCurrentLoginInformation redisCurrentLoginInformation;
    @Autowired
    private IdentityFeignClient identityFeignClient;

    @Resource
    private AccountService accountService;
    @Resource
    private MenuService menuService;
    @Resource
    private TenantService tenantService;

    @Override
    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        AccountDto accountDto = null;
        if (redisCurrentLoginInformationInput.getAccountId() != null) {
            accountDto = accountService.findByIdToDto(redisCurrentLoginInformationInput.getAccountId());
        }
        if (accountDto == null || accountDto.getId() == null) {
            //TODO 以后解决token 这里直接获取当前用户，不要调用
//      Result<String> securityUserId = identityFeignClient.getSecurityUserId();
//      Result<String> userId = identityFeignClient.getUserId();
//      List<AccountDto> accountDtos = accountService.findAllByUserId(userId.getMessage()));
//        Object principal = SecurityUtils.getAuthentication().getPrincipal();
//        PrincipalDetails principalDetails = PrincipalUtils.toPrincipalDetails((DefaultSecurityUser) principal);

            List<AccountDto> accountDtos = accountService.findAllByUserId(SecurityUtils.getUsername());
//      List<AccountDto> accountDtos = accountService.findAllByUserId(securityUserId.getData());
            if (accountDtos.size() == 0) {
                throw new CommonException("No account was found, please log in again");
            }
            accountDto = accountDtos.get(0);
        }
        CurrentLoginInformation currentLoginInformation = new CurrentLoginInformation();
        currentLoginInformation.setUserId(accountDto.getUserId());
        currentLoginInformation.setAccountId(accountDto.getId());
        currentLoginInformation.setTenantId(accountDto.getTenantId());
        currentLoginInformation.setAccountType(accountDto.getType());
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
