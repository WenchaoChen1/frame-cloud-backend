package com.gstdev.template.service.system.service;


import com.gstdev.template.service.system.pojo.domain.Email;
import com.gstdev.template.service.system.enums.EmailTypeEnum;
import org.thymeleaf.context.Context;

public interface EmailService {

  void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception;

  Email findByTokenAndType(String token, int type);

  Email findByTokenAndReceiverEmail(String token, String email);




}
