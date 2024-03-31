// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.identity.service;

import com.frame.template.service.identity.domain.User;
import com.frame.template.service.identity.contract.UserDto;
import com.frame.template.service.identity.contract.UserInput;
import com.frame.template.service.identity.contract.query.PostQueryCriteria;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Result<Object> getUserData(PostQueryCriteria criteria, Pageable pageable);

  Result<UserDto> create(UserInput userInput);

  Result<Object> update(UserInput userInput);

  Result<Object> delete(String userId);

  User getByUsername(String username);

  User getUserByUserId(String userId);

  String findByUserId(String userId);

  User findByEmail(String username);
}
