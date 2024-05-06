//// ====================================================
////
//// This file is part of the GstDev Cloud Platform.
////
//// Create by GstDev Cloud <support@gstdev.com>
//// Copyright (c) 2022-2025 gstdev.com
////
//// ====================================================
//
//package com.frame.template.service.identity.service;
//
//
//import cn.hutool.core.util.ObjectUtil;
//import com.frame.template.service.identity.domain.User;
//import com.frame.template.common.constant.ServiceConstants;
//import com.frame.template.common.utils.CryptoUtils;
//import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
//import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
//import com.gstdev.cloud.oauth2.core.definition.domain.FrameGrantedAuthority;
//import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
//import com.gstdev.cloud.starter.oauth2.authentication.server.model.UserAuth;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.Resource;
//
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//@Slf4j
//@Service
//public class UserDetailServiceImpl implements StrategyUserDetailsService {
//    @Resource
//    private UserService userService;
//
//    @Override
//    public DefaultSecurityUser findUserDetailsByUsername(String username) throws AuthenticationException {
//        //    User user = userService.getByUsername(username);
//        User user = userService.findByEmail(username);
//        Set<String> role = new HashSet<>();
//        DefaultSecurityUser defaultSecurityUser = new DefaultSecurityUser(user.getUserId()
//            , user.getUserId()
//            , user.getPassword()
//            , true
//            , true
//            , true
//            , true
//            , getAuthorities(new ArrayList<>(List.of("USER")))
//            , role
//            , "123123"
//            , "ava123213"
//        );
//        if (ObjectUtil.isNotNull(user) && ObjectUtil.isNotNull(user.getUsername())) {
////            defaultSecurityUser.setUsername(user.getUserId());
////            defaultSecurityUser.setPassword(passwordEncoder.encode(password));
//        }
//        return defaultSecurityUser;
//    }
//
//    @Override
//    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
//        return null;
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
//        if (roles == null) {
//            return Collections.emptyList();
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//
//        return authorities;
//    }
//
//
//}
