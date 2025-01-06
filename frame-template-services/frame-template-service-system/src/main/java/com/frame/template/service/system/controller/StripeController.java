//package com.frame.template.service.system.controller;
//
//import com.gstdev.cloud.base.definition.domain.Result;
//import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
//import com.gstdev.cloud.service.system.domain.pojo.sysPermission.PermissionManageQO;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.Resource;
//import org.springframework.core.env.Environment;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/aaaaaaaat")
//public class StripeController {
//    @Resource
//    Environment environment;
//    @PostConstruct
//    public void postConstruct() {
//        Environment environment1 = environment;
//        String property = environment.getProperty("gstdev.cloud.swagger.enabled");
//        System.out.println(property);
//        System.out.println("aaaaaaaaa11112");
//    }
//    @GetMapping("/a")
//    @Operation(summary = "a")
//    public void a() {
//        System.out.println("aaaaaaaaa1111");
//        SecurityContext securityContext = SecurityUtils.getSecurityContext();
//        System.out.println(SecurityUtils.getTokenValue());
//    }
//    @PostMapping("/{aaa}/get-permission-manage-page")
//    @Operation(summary = "get-permission-manage-page")
//    public Result<Map<String, Object>> getPermissionManagePage(@RequestBody PermissionManageQO permissionManageQO) {
//        System.out.println("aaaaaaaaa1111");
//        SecurityContext securityContext = SecurityUtils.getSecurityContext();
//        System.out.println(SecurityUtils.getTokenValue());
//        return null;
//    }
//
//}
//
