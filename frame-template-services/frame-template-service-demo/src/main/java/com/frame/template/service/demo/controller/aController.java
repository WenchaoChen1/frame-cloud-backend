package com.frame.template.service.demo.controller;

import com.frame.template.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class aController {
  @GetMapping("/a")
  public String a() {
    System.out.println("aaaaaaaaaaa");
//    ServletUtils.getRequest()
//    System.out.println(SecurityUtils.getUserDetails());
    return "/demo";
  }
  @GetMapping("/bb")
  public String testa() {
    System.out.println("aaaaaaaaaaa");
//    ServletUtils.getRequest()
    return "System.out.println(SecurityUtils.getUserDetails());";
  }
}
