package com.frame.template.service.system.feign;


import com.frame.template.common.constant.ServiceConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ServiceConstants.SERVICE_NAME_IDENTITY)
public interface IdentityClient {
  @GetMapping(value = "/user/a")
  String a();


  @PostMapping(value = "/oauth2/token")
  Object login(@RequestHeader("authorization") String authorization, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("rememberMe")Boolean rememberMe, @RequestParam("grant_type")String grant_type);


}
