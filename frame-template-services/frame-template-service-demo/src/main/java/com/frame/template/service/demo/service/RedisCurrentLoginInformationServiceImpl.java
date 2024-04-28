package com.frame.template.service.demo.service;

import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformation;
import com.frame.template.common.redis.currentLoginInformation.RedisCurrentLoginInformationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisCurrentLoginInformationServiceImpl implements RedisCurrentLoginInformationService {


    @Autowired
    private RedisCurrentLoginInformation redisCurrentLoginInformation;

    @Override
    public Result<Object> addByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        CurrentLoginInformation CurrentLoginInformation = new CurrentLoginInformation();
        CurrentLoginInformation.setAccountId(redisCurrentLoginInformationInput.getAccountId());
        redisCurrentLoginInformation.addByTokenCurrentLoginInformation(CurrentLoginInformation);
        return Result.success();
    }

    @Override
    public Result<Object> updateByTokenCurrentLoginInformation(RedisCurrentLoginInformationInput redisCurrentLoginInformationInput) {
        CurrentLoginInformation CurrentLoginInformation = new CurrentLoginInformation();
        CurrentLoginInformation.setAccountId(redisCurrentLoginInformationInput.getAccountId());
        redisCurrentLoginInformation.updateByTokenCurrentLoginInformation(CurrentLoginInformation);
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
