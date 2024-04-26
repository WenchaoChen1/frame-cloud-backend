package com.frame.template.common.utils;// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

import com.frame.template.common.constant.RedisConstants;
import com.frame.template.common.constant.ServletConstants;
import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
import com.gstdev.cloud.cache.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.annotation.Resource;

@Slf4j
public class SecurityUtils {

  //    @Resource
//    private UserDetailService<User> userDetailService;
  @Resource
  private static RedisUtils redisUtils;


  public static SecurityContext getSecurityContext() {
    return SecurityContextHolder.getContext();
  }

  public static Authentication getAuthentication() {
    return getSecurityContext().getAuthentication();
  }

  public static Object getUserDetails() {
    Authentication authentication = getAuthentication();

    return authentication.getName();
  }




  public static String getUserId() {
    Authentication authentication = getAuthentication();
    String name = getAuthentication().getName();
    return name;
  }

  public static String getAccountId() {
    SecurityUtils s = new SecurityUtils();
    CurrentLoginInformation currentLogininformation = s.getCurrentLogininformation();
    return "getCurrentLogininformation().getAccountId();";
//      return ServletUtils.getHeader("Authorization");
  }

  public CurrentLoginInformation getCurrentLogininformation() {
    CurrentLoginInformation currentLogininformation = (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
    return (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
//      return ServletUtils.getHeader("Authorization");
  }
}
