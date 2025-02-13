package com.frame.template.service.basic.security.service;

import com.frame.template.service.basic.security.domain.LoginUser;
import com.frame.template.service.basic.security.domain.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setEmail(username);
        User sysUser=userService.findByUser(user);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        return new LoginUser(sysUser);
    }
}
