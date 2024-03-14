package com.gstdev.template.service.email.controller;


import com.gstdev.template.service.email.enums.EmailTypeEnum;
import com.gstdev.template.service.email.pojo.domain.Email;
import com.gstdev.template.service.email.pojo.domain.vo.UserDto;
import com.gstdev.template.service.email.service.EmailService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;



@Slf4j
@RestController
@RequestMapping("/v1/email")
public class EmailController{

  @Resource
  private EmailService emailService;

  @PostMapping("/send-email")
  @ApiOperation("发送邮件")
  public void sendEmail(@RequestBody Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception {
    emailService.sendEmail(email,context,templateName,emailTypeEnum);
  }

  @PostMapping("/invite-user")
  @ApiOperation("创建用户时时发送邮件")
  public void inviteUser(@RequestBody UserDto userDto) {
    emailService.inviteUser(userDto);
  }
  @GetMapping("/a")
  @ApiOperation("创建用户时时发送邮件")
  public void a() {
    System.out.println("aaaaaaaaaaaaaaaa");
//    emailService.inviteUser(userDto);
  }
}
