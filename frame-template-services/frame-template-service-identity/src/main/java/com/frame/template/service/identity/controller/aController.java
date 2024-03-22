package com.frame.template.service.identity.controller;

import com.frame.template.common.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class aController {
  @GetMapping("/a")
  public String a() {
//    ServletUtils.getRequest()
    System.out.println(SecurityUtils.getUserDetails());
    return "a";
  }
  @GetMapping("/b")
  public String b() {
//    ServletUtils.getRequest()
    System.out.println(SecurityUtils.getUserDetails());
    return "b";
  }
  /**
   * 获取用户信息
   * @return
   */
  @GetMapping("/user")
  public Authentication oauth2UserInfo(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication == null){
      throw new RuntimeException("无有效认证用户！");
    }
    return authentication;
  }
}
