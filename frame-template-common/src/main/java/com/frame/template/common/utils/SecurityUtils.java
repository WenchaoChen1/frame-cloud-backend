//package com.frame.template.common.utils;// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//import com.frame.template.common.constant.RedisConstants;
//import com.frame.template.common.constant.ServletConstants;
//import com.frame.template.common.redis.currentLoginInformation.CurrentLoginInformation;
//import com.gstdev.cloud.cache.redis.utils.RedisUtils;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class SecurityUtils {
//
//    //    @Resource
////    private UserDetailService<User> userDetailService;
//    @Resource
//    private static RedisUtils redisUtils;
//
//    public static String getAccountId() {
//        SecurityUtils s = new SecurityUtils();
//        CurrentLoginInformation currentLogininformation = s.getCurrentLogininformation();
//        return "getCurrentLogininformation().getAccountId();";
////      return ServletUtils.getHeader("Authorization");
//    }
//
//    public CurrentLoginInformation getCurrentLogininformation() {
//        CurrentLoginInformation currentLogininformation = (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
//        return (CurrentLoginInformation) redisUtils.get(RedisConstants.buildKey(RedisConstants.KET_CURRENT_LOGIN_ACCOUNT_ID, ServletUtils.getHeader(ServletConstants.AUTHORIZATION)));
////      return ServletUtils.getHeader("Authorization");
//    }
//}
