package com.frame.template.service.email.service;


import com.frame.template.service.email.enums.EmailTypeEnum;
import com.frame.template.service.email.pojo.domain.Email;
import com.frame.template.service.email.pojo.domain.vo.UserDto;
import org.thymeleaf.context.Context;

public interface EmailService {

    void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception;

    Email findByTokenAndType(String token, int type);

    Email findByTokenAndReceiverEmail(String token, String email);

    void inviteUser(UserDto userDto);

}
