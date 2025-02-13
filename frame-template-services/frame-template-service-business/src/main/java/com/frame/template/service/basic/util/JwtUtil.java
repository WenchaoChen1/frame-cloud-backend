package com.frame.template.service.basic.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.frame.template.service.basic.security.domain.User;

import java.util.Calendar;

public class JwtUtil {
    private static final String SING = "LoginToken";
    public static String getJwtTokenByUesr(User user) {
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //默认7天过期
        calendar.add(Calendar.MINUTE, 30);
        //新建一个JWT的Builder对象
        JWTCreator.Builder builder = JWT.create();
        //将map集合中的数据设置进payload
        builder
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail());

        //设置过期时间和签名
        String sign = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SING));
        return sign;
    }
}
