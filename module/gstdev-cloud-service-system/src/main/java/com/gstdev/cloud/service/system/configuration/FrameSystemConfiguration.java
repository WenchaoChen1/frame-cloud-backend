
package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.mapper.*;
import com.gstdev.cloud.service.system.repository.*;
import com.gstdev.cloud.service.system.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
    "com.gstdev.cloud.service.system.pojo.entity",
})
@EnableJpaRepositories(basePackages = {
    "com.gstdev.cloud.service.system.repository",
})
@ComponentScan(basePackages = {
    "com.gstdev.cloud.service.system.mapper",
//    "com.gstdev.cloud.service.system.feign.service",
})
@Import({FrameSystemServiceConfiguration.class})
public class FrameSystemConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Auto Configure.");
    }

}
