package com.frame.template.service.identity.configuration;


import com.frame.template.service.identity.feign.FeignRemoteUserDetailsService;
import com.frame.template.service.identity.service.LocalUserDetailsService;
import com.frame.template.service.identity.service.RemoteUserDetailsService;
import com.frame.template.service.identity.service.UserService;
import com.gstdev.cloud.oauth2.core.definition.strategy.StrategyUserDetailsService;
import com.gstdev.cloud.rest.condition.annotation.ConditionalOnDistributedArchitecture;
import com.gstdev.cloud.rest.condition.annotation.ConditionalOnLocalDataAccess;
import com.gstdev.cloud.rest.condition.annotation.ConditionalOnRemoteDataAccess;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 分布式架构配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnDistributedArchitecture
public class DistributedArchitectureConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DistributedArchitectureConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- Module [Distributed Architecture] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
//    @ConditionalOnLocalDataAccess
    static class DataAccessStrategyLocalConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public StrategyUserDetailsService localUserDetailsService(UserService userService) {
            log.debug("[GstDev Cloud] |- Strategy [Local User Details Service] Auto Configure.");
            return new LocalUserDetailsService(userService);
        }
//        @Bean
//        @ConditionalOnMissingBean
//        public StrategyUserDetailsService localUserDetailsService(SysUserService sysUserService, SocialAuthenticationHandler socialAuthenticationHandler) {
//            log.debug("[GstDev Cloud] |- Strategy [Local User Details Service] Auto Configure.");
//            return new LocalUserDetailsService(sysUserService, socialAuthenticationHandler);
//        }

//        @Bean
//        @ConditionalOnMissingBean
//        public StrategyPermissionDetailsService herodotusLocalPermissionDetailsService(SysPermissionService sysPermissionService) {
//            HerodotusLocalPermissionDetailsService herodotusLocalPermissionDetailsService = new HerodotusLocalPermissionDetailsService(sysPermissionService);
//            log.debug("[GstDev Cloud] |- Strategy [Local Permission Details Service] Auto Configure.");
//            return herodotusLocalPermissionDetailsService;
//        }
    }
//
//    @Configuration(proxyBeanMethods = false)
//    @ConditionalOnRemoteDataAccess
//    @EnableFeignClients(basePackages = {"com.frame.template.service.identity.feign"})
//    static class DataAccessStrategyRemoteConfiguration {
//
//        @Bean
//        @ConditionalOnMissingBean
//        public StrategyUserDetailsService herodotusRemoteUserDetailsService(FeignRemoteUserDetailsService feignRemoteUserDetailsService) {
////        public StrategyUserDetailsService herodotusRemoteUserDetailsService(RemoteUserDetailsService remoteUserDetailsService, RemoteSocialDetailsService remoteSocialDetailsService) {
//            log.debug("[GstDev Cloud] |- Strategy [Remote User Details Service] Auto Configure.");
//            return new RemoteUserDetailsService(feignRemoteUserDetailsService);
//        }
//
////        @Bean
////        @ConditionalOnMissingBean
////        public StrategyPermissionDetailsService HerodotusRemotePermissionDetailsService(RemoteAuthorityDetailsService remoteAuthorityDetailsService) {
////            RemotePermissionDetailsService herodotusRemotePermissionDetailsService = new RemotePermissionDetailsService(remoteAuthorityDetailsService);
////            log.debug("[GstDev Cloud] |- Strategy [Remote Permission Details Service] Auto Configure.");
////            return herodotusRemotePermissionDetailsService;
////        }
//    }
}
