package com.frame.template.service.system.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aaaaaaaat")
public class SysAccountController {
    @PostConstruct
    public void postConstruct() {
        System.out.println("aaaaaaaaa11112");
    }
}
