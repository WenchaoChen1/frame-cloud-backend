package com.frame.template.autoconfigure.service.system.configuration;


import com.gstdev.cloud.service.system.configuration.FrameSystemConfiguration;
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
    "com.frame.template.common.redis.currentLoginInformation",
    "com.frame.template.autoconfigure.service.system.controller",
    "com.frame.template.autoconfigure.service.system.service",
    "com.frame.template.autoconfigure.service.system.feign.service",
    "com.frame.template.autoconfigure.service.system.listener",
    "com.frame.template.autoconfigure.service.system.processor",
    "com.gstdev.cloud",
})
@EntityScan(value = {"com.frame.template.service.system"})
@Import({FrameSystemConfiguration.class})
public class AutoSystemConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoSystemConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- Module [Auto System Configuration] Auto Configure.");
    }


}
