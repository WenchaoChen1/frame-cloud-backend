package com.frame.template.service.basic.security;

import com.frame.template.service.basic.security.domain.User;
import com.frame.template.service.basic.security.service.LoginService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.rest.core.controller.ResultController;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class LoginController implements ResultController {
    @Resource
    private LoginService loginService;
   // @Tag(name = "Musician Manage")
    @PostMapping("/login")
    @Operation(summary = "get-musician-manage-detail")
    public Result<Map<String, Object>> login(@RequestBody User user) {

        return result(this.loginService.login(user));
    }

}
