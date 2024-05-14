// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.autoconfigure.service.identity.controller;

import com.frame.template.autoconfigure.service.identity.pojo.dto.UserDto;
import com.frame.template.autoconfigure.service.identity.pojo.UserInput;
import com.frame.template.autoconfigure.service.identity.pojo.query.PostQueryCriteria;
import com.frame.template.autoconfigure.service.identity.service.UserService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public Result<UserDto> create(@Validated @RequestBody UserInput userInput) {
        return userService.create(userInput);
    }

    @PostMapping("/save")
    public Result<UserDto> save(@Validated @RequestBody UserInput userInput) {
        return userService.create(userInput);
    }

    @PutMapping
    public Result<Object> update(@Validated @RequestBody UserInput userInput) {
        return userService.update(userInput);
    }

    @DeleteMapping("{userId}")
    public Result<Object> delete(@PathVariable String userId) {
        return userService.delete(userId);
    }

    @GetMapping
    public Result<Object> getUserData(PostQueryCriteria criteria, Pageable pageable) {
        return userService.getUserData(criteria, pageable);
    }
}
