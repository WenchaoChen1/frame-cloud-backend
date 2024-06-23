package com.frame.template.autoconfigure.service.identity.service;

import com.frame.template.autoconfigure.service.identity.feign.FeignRemoteUserDetailsService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>Description: UserDetail远程调用服务 </p>
 */
public class RemoteUserDetailsService implements StrategyUserDetailsService {

    private final FeignRemoteUserDetailsService feignRemoteUserDetailsService;

    public RemoteUserDetailsService(FeignRemoteUserDetailsService feignRemoteUserDetailsService) {
        this.feignRemoteUserDetailsService = feignRemoteUserDetailsService;
    }

    @Override
    public DefaultSecurityUser findUserDetailsByUsername(String username) throws UsernameNotFoundException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Result<DefaultSecurityUser> result = feignRemoteUserDetailsService.findByUsername(username);
        return result.getData();
    }

    @Override
    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
        return null;
    }


}
