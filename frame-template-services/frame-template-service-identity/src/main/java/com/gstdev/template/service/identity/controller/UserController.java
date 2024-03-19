// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.identity.controller;

import com.gstdev.cloud.commons.web.Result;
import com.gstdev.template.common.utils.SecurityUtils;
import com.gstdev.template.service.identity.contract.UserDto;
import com.gstdev.template.service.identity.contract.UserInput;
import com.gstdev.template.service.identity.contract.query.PostQueryCriteria;
import com.gstdev.template.service.identity.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//@Api(tags = "岗位管理")
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
//  @ApiOperation(value = "添加新岗位")
  public Result<UserDto> create(@Validated @RequestBody UserInput userInput) {
    return userService.create(userInput);
  }

  @PutMapping
//  @ApiOperation(value = "编辑岗位")
  public Result<Object> update(@Validated @RequestBody UserInput userInput) {
    return userService.update(userInput);
  }

  @DeleteMapping("{userId}")
//  @ApiOperation(value = "删除岗位")
  public Result<Object> delete(@PathVariable String userId) {
    return userService.delete(userId);
  }

  @GetMapping
//  @ApiOperation(value = "岗位分页列表")
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
