package com.frame.template.common.redis.currentLoginInformation;

import com.frame.template.common.constant.RedisConstants;
import com.frame.template.common.constant.ServletConstants;
import com.gstdev.cloud.cache.redis.utils.RedisUtils;
import com.gstdev.cloud.base.definition.domain.Result;
import com.frame.template.common.utils.ServletUtils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
//@DependsOn("redisUtils")
public class RedisCurrentLoginInformationImpl implements RedisCurrentLoginInformation {

    //    @Resource
//    private RedisUtils redisUtils;
    @Override
    public Result<Object> addByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation) {
        RedisUtils.set(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)), currentLoginInformation, RedisConstants.TIMEOUT, TimeUnit.HOURS);
        return Result.success();
    }

    @Override
    //add和update一样的功能
    public Result<Object> updateByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation) {
        RedisUtils.set(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)), currentLoginInformation, RedisConstants.TIMEOUT, TimeUnit.HOURS);
        return Result.success();
    }

    @Override
    public Result<Object> deleteByTokenCurrentLoginInformation() {
        RedisUtils.del(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
        return Result.success();
    }

    @Override
    public CurrentLoginInformation getCurrentLoginInformation() {
        CurrentLoginInformation currentLogininformation = null;
        try {
            currentLogininformation = (CurrentLoginInformation) RedisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
        } catch (Exception e) {
            return null;
        }
//    CurrentLoginInformation currentLogininformation = (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
        return currentLogininformation;
    }

    @Override
    public String getCurrentLoginAccountId() {
        CurrentLoginInformation loginInformation = getCurrentLoginInformation();
        if (loginInformation == null) {
            return null;
        }
        return loginInformation.getUserId();
    }

    @Override
    public String getCurrentLoginTenantId() {
        CurrentLoginInformation loginInformation = getCurrentLoginInformation();
        if (loginInformation == null) {
            return null;
        }
        return loginInformation.getTenantId();
    }
}
