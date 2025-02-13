package com.frame.template.service.basic.security.service;

import com.frame.template.service.basic.BusinessApplication;
import com.frame.template.service.basic.security.domain.LoginUser;
import com.frame.template.service.basic.security.domain.User;
import com.frame.template.service.basic.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.frame.template.service.basic.security.service.UserService;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userServcie;
    @Override
    public HashMap<String, Object> login(User user) {
        User existUser = userServcie.findByUser(user);
        if(existUser==null){
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setThirdId(user.getThirdId());
            newUser.setUserFrom("Google");
            newUser.setUserType(user.getUserType());
            userServcie.save(newUser);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getThirdId());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //验证没通过
       // if(!authentication.isAuthenticated()){

        //}
        LoginUser loginUser= (LoginUser) authentication.getPrincipal();
        HashMap<String, Object> hashMap=new HashMap<>();
        String userId=loginUser.getUser().getId();
        hashMap.put("token", JwtUtil.getJwtTokenByUesr(loginUser.getUser()));
        hashMap.put("userId", userId);
        hashMap.put("role", user.getUserType());
        //redisTemplate.opsForValue().set("loginUser"+userId,loginUser);
        //认证通过生成jwt
        return hashMap;
    }

}
