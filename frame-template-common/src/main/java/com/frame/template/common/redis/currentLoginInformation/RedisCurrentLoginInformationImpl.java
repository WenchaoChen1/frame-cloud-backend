package com.frame.template.common.redis.currentLoginInformation;

import com.frame.template.common.constant.RedisConstants;
import com.frame.template.common.constant.ServletConstants;
import com.gstdev.cloud.commons.utils.RedisUtils;
import com.gstdev.cloud.commons.web.Result;
import com.frame.template.common.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCurrentLoginInformationImpl implements RedisCurrentLoginInformation {

  @Autowired
  private RedisUtils redisUtils;

  public Result<Object> addByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation) {
    redisUtils.set(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)), currentLoginInformation, RedisConstants.TIMEOUT, TimeUnit.HOURS);
    return Result.success();
  }

  //add和update一样的功能
  public Result<Object> updateByTokenCurrentLoginInformation(CurrentLoginInformation currentLoginInformation) {
    redisUtils.set(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)), currentLoginInformation, RedisConstants.TIMEOUT, TimeUnit.HOURS);
    return Result.success();
  }

  public Result<Object> deleteByTokenCurrentLoginInformation() {
    redisUtils.del(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
    return Result.success();
  }

  public CurrentLoginInformation getCurrentLoginInformation() {
    CurrentLoginInformation currentLogininformation = null;
    try {
      currentLogininformation = (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
    }catch (Exception e){
      return null;
    }
//    CurrentLoginInformation currentLogininformation = (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
    return currentLogininformation;
  }

  public String getCurrentLoginAccountId() {
    CurrentLoginInformation loginInformation = getCurrentLoginInformation();
    if(loginInformation==null){
      return null;
    }
    return loginInformation.getUserId();
  }

  public String getCurrentLoginTenantId() {
    CurrentLoginInformation loginInformation = getCurrentLoginInformation();
    if(loginInformation==null){
      return null;
    }
    return loginInformation.getTenantId();
  }
}
