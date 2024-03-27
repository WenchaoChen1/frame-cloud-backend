// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.identity.service;


import cn.hutool.core.util.ObjectUtil;
import com.frame.template.service.identity.domain.User;
import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.common.utils.CryptoUtils;
import com.gstdev.cloud.oauth2.authentication.model.UserAuth;
import com.gstdev.cloud.oauth2.authentication.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailService {

  @Resource
  private UserService userService;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Override
  public UserAuth loadUserByUsername(String username) {
//    User user = userService.getByUsername(username);
    User user = userService.findByEmail(username);
    // 数据库密码解密
    byte[] decryptWord = CryptoUtils.asymDecrypt(Base64.getDecoder().decode(user.getPassword()), ServiceConstants.ASYM_PRIVATE_KEY);
    String password = new String(decryptWord, StandardCharsets.UTF_8);
    log.debug(password.equals(user.getPassword()) + " ");
    if (password.equals(user.getPassword())) {

    }
    UserAuth userAuth = new UserAuth();
    if (ObjectUtil.isNotNull(user) && ObjectUtil.isNotNull(user.getUsername())) {
      userAuth.setUsername(user.getUserId());
      userAuth.setPassword(passwordEncoder.encode(password));
    }

    return userAuth;
  }

  /**
   * 定义获取当前用户信息实体
   * implements UserDetailService<T>  自定义T的类型
   *
   * @param userId 用户ID
   * @return T
   */
  @Override
  public User loadUserByUserId(String userId) {
    return userService.getUserByUserId(userId);
  }

}
