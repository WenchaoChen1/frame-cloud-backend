package com.frame.template.service.identity.configuration;

import com.frame.template.autoconfigure.service.identity.configuration.DistributedArchitectureConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({
    DistributedArchitectureConfiguration.class,
})
public class IdentityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(IdentityConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Frame] |- Service [identity AutoConfigure] Auto Configure.");
    }
}
