package com.frame.template.service.identity.service;

import com.frame.template.service.identity.feign.FeignRemoteUserDetailsService;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>Description: UserDetail远程调用服务 </p>
 *
 */
public class RemoteUserDetailsService implements StrategyUserDetailsService {

    private final FeignRemoteUserDetailsService feignRemoteUserDetailsService;

    public RemoteUserDetailsService(FeignRemoteUserDetailsService feignRemoteUserDetailsService) {
        this.feignRemoteUserDetailsService = feignRemoteUserDetailsService;
    }

    @Override
    public DefaultSecurityUser findUserDetailsByUsername(String username) throws UsernameNotFoundException {
        Result<DefaultSecurityUser> result = feignRemoteUserDetailsService.findByUsername(username);
        return result.getData();
    }

    @Override
    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
        return null;
    }


}
