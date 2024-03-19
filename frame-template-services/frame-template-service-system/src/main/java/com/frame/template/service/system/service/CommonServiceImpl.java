// ====================================================
//
// This file is part of the GstDev Verity Platform.
//
// Create by GstDev <support@gstdev.com>
// Copyright (c) 2020-2020 gstdev.com
//
// ====================================================

package com.frame.template.service.system.service;


import com.frame.template.service.system.pojo.domain.User;
import com.frame.template.service.system.repository.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "invite")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {
  @Resource
  private UserRepository userRepository;

  @Resource
  private EmailService emailService;


  @Override
  public String checkIfUserExist(String emailAddress){
    String validationMessage = null;
    Optional<User> user = userRepository.findByEmail(emailAddress);
    if(user.isPresent() && !ObjectUtils.isEmpty(user)){
      validationMessage = "The email address already exists";
    }
    return validationMessage;
  }


}
