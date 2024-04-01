package com.frame.template.service.system.feign.service;

import com.frame.template.service.system.feign.vo.UserDto;
import com.frame.template.service.system.feign.EmailFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: zcy
 * @date: 2022/12/9
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailFeignService {
  private final EmailFeignClient emailFeignClient;


//  public void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception{
//      emailFeignClient.sendEmail(email,context, templateName, emailTypeEnum);
//  }

  public void inviteUser(@RequestBody UserDto userDto) {
    emailFeignClient.inviteUser(userDto);
//    emailFeignClient.a();
  }

}
