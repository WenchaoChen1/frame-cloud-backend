package com.frame.template.autoconfigure.service.system.configuration;


import com.gstdev.cloud.service.system.configuration.FrameSystemConfiguration;
import com.gstdev.cloud.starter.ouath2.resource.server.configuration.ResourceServerAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(basePackages = {"com.frame.template.autoconfigure.service.system.feign"})
@ComponentScan(value = {
        "com.gstdev.cloud.rest.autoconfigure",
//        "com.frame.template.common.redis.currentLoginInformation",
        "com.frame.template.autoconfigure.service.system.feign.service",
        "com.frame.template.autoconfigure.service.system.listener",
        "com.frame.template.autoconfigure.service.system.processor",
//    "com.gstdev.cloud",
//    "com.gstdev.cloud.service.system",
//    "com.gstdev.cloud.springframework.openfeign",
//    "com.gstdev.cloud.cache.jetcache",
//    "com.gstdev.cloud.rest.autoconfigure",
//    "com.gstdev.cloud.web",
//    "com.gstdev.cloud.starter",
//    "com.gstdev.cloud.base",
//    "com.gstdev.cloud.base.core.json.jackson2.utils",
//    "com.gstdev.cloud.cache",
//    "com.gstdev.cloud.message",
//    "com.gstdev.cloud.rest",
//    "com.gstdev.cloud.captcha",
//    "com.gstdev.cloud.data",
//    "com.gstdev.cloud.oauth2"
})
@EntityScan(value = {"com.frame.template.service.system"})
@Import({FrameSystemConfiguration.class, ResourceServerAutoConfiguration.class})
public class AutoSystemConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoSystemConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- Module [Auto System Configuration] Auto Configure.");
    }


}
