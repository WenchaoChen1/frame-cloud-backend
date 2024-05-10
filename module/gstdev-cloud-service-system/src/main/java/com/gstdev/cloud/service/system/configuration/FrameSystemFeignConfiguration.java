
package com.gstdev.cloud.service.system.configuration;

import com.gstdev.cloud.service.system.mapper.*;
import com.gstdev.cloud.service.system.repository.*;
import com.gstdev.cloud.service.system.service.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:  模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class FrameSystemFeignConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FrameSystemFeignConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[GstDev Cloud] |- SDK [Frame System Fegin Auto Configure.");
    }

}
