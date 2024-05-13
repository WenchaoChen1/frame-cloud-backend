// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.autoconfigure.service.identity.service;

import com.frame.template.autoconfigure.service.identity.pojo.UserInput;
import com.frame.template.autoconfigure.service.identity.pojo.dto.UserDto;
import com.frame.template.autoconfigure.service.identity.pojo.entity.User;
import com.frame.template.autoconfigure.service.identity.pojo.query.PostQueryCriteria;
import com.gstdev.cloud.base.definition.domain.Result;
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
