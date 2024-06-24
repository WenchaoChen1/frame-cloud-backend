package com.frame.template.service.email.controller;


import com.frame.template.service.email.enums.EmailTypeEnum;
import com.frame.template.service.email.pojo.entity.Email;
import com.frame.template.service.email.pojo.vo.UserDto;
import com.frame.template.service.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;


@Slf4j
@RestController
@RequestMapping("/v1/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @PostMapping("/send-email")
    @Operation(summary = "发送邮件")
    public void sendEmail(@RequestBody Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception {
        emailService.sendEmail(email, context, templateName, emailTypeEnum);
    }

    @PostMapping("/invite-user")
    @Operation(summary = "创建用户时时发送邮件")
    public void inviteUser(@RequestBody UserDto userDto) {
        emailService.inviteUser(userDto);
    }

    @GetMapping("/a")
    @Operation(summary = "创建用户时时发送邮件")
    public void a() {
        System.out.println("aaaaaaaaaaaaaaaa");
//    emailService.inviteUser(userDto);
    }
}
