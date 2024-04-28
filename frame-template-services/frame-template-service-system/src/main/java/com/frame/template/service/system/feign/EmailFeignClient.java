package com.frame.template.service.system.feign;


import com.frame.template.common.constant.ServiceConstants;
import com.frame.template.service.system.feign.vo.UserDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = ServiceConstants.SERVICE_NAME_EMAIL)
public interface EmailFeignClient {

//  @PostMapping(value = "/v1/email/send-email")
//  void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum);

    @PostMapping(value = "/v1/email/invite-user")
    void inviteUser(@RequestBody UserDto userDto);

    @GetMapping(value = "/v1/email/a")
    void a();
}
