// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.identity.controller;

import com.frame.template.service.identity.contract.UserDto;
import com.frame.template.service.identity.contract.UserInput;
import com.frame.template.service.identity.contract.query.PostQueryCriteria;
import com.frame.template.service.identity.service.UserService;
import com.frame.template.common.utils.SecurityUtils;
import com.gstdev.cloud.base.definition.domain.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/a")

    public String a() {

        return "identity a";
    }

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
        Object userDetails = SecurityUtils.getUserDetails();
        return userService.getUserData(criteria, pageable);
    }

    @GetMapping("get_security_user_id")
    public Result<String> getSecurityUserId() {
        return Result.success(SecurityUtils.getUserId());
    }

    @GetMapping("get_user_id")
    public Result<String> getUserId() {
//    return Result.success(userService.findByUserId(SecurityUtils.getUserId()));
        return Result.success(SecurityUtils.getUserId());
    }
}
