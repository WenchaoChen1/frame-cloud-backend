package com.frame.template.autoconfigure.service.identity.service;

import cn.hutool.core.util.ObjectUtil;
import com.frame.template.autoconfigure.service.identity.pojo.entity.User;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * <p>Description: UserDetail本地直联服务 </p>
 */
public class LocalUserDetailsService implements StrategyUserDetailsService {

//    private final SysUserService sysUserService;
//    private final SocialAuthenticationHandler socialAuthenticationHandler;
//
//    public LocalUserDetailsService(SysUserService sysUserService, SocialAuthenticationHandler socialAuthenticationHandler) {
//        this.sysUserService = sysUserService;
//        this.socialAuthenticationHandler = socialAuthenticationHandler;
//    }
//
//    @Override
//    public DefaultSecurityUser findUserDetailsByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = sysUserService.findByUsername(username);
//        return this.convertSysUser(sysUser, username);
//    }
//
//    @Override
//    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) {
//        return socialAuthenticationHandler.authentication(source, accessPrincipal);
//    }

    private final UserService userService;

    public LocalUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public DefaultSecurityUser findUserDetailsByUsername(String username) throws AuthenticationException {
        //    User user = userService.getByUsername(username);
        User user = userService.findByEmail(username);
        Set<String> role = new HashSet<>();
        DefaultSecurityUser defaultSecurityUser = new DefaultSecurityUser(user.getUserId()
                , user.getUserId()
                , user.getPassword()
                , true
                , true
                , true
                , true
                , getAuthorities(new ArrayList<>(List.of("USER")))
                , role
                , "123123"
                , "ava123213"
        );
        if (ObjectUtil.isNotNull(user) && ObjectUtil.isNotNull(user.getUsername())) {
//            defaultSecurityUser.setUsername(user.getUserId());
//            defaultSecurityUser.setPassword(passwordEncoder.encode(password));
        }
        return defaultSecurityUser;
    }

    @Override
    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        if (roles == null) {
            return Collections.emptyList();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}
