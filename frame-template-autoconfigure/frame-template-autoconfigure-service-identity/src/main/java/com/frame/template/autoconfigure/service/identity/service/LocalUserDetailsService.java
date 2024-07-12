package com.frame.template.autoconfigure.service.identity.service;

import com.gstdev.cloud.base.definition.domain.oauth2.AccessPrincipal;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: UserDetail本地直联服务 </p>
 */
public class LocalUserDetailsService implements StrategyUserDetailsService {


    @Override
    public DefaultSecurityUser findUserDetailsByUsername(String username) throws AuthenticationException {
        return null;
    }

    @Override
    public DefaultSecurityUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException {
        return null;
    }
}
