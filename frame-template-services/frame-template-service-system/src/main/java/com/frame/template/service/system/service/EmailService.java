package com.frame.template.service.system.service;


import com.frame.template.service.system.enums.EmailTypeEnum;
import com.frame.template.service.system.pojo.domain.Email;
import org.thymeleaf.context.Context;

public interface EmailService {

  void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception;

  Email findByTokenAndType(String token, int type);

  Email findByTokenAndReceiverEmail(String token, String email);




}
