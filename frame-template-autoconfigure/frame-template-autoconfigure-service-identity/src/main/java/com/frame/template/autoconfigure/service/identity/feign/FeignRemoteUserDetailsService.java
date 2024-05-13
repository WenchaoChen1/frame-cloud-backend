package com.frame.template.autoconfigure.service.identity.feign;

import com.frame.template.common.constant.ServiceConstants;
import com.gstdev.cloud.base.definition.domain.Result;
import com.gstdev.cloud.oauth2.core.definition.domain.DefaultSecurityUser;
import com.gstdev.cloud.springframework.openfeign.annotation.Inner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Description: 远程 User Details 服务 </p>
 */
@FeignClient(contextId = "feignRemoteUserDetailsService", value = ServiceConstants.SERVICE_NAME_SYSTEM)
public interface FeignRemoteUserDetailsService {

    @Inner
    @GetMapping("/v1/user/security/sign-in/{username}")
    Result<DefaultSecurityUser> findByUsername(@PathVariable("username") String username);
}
