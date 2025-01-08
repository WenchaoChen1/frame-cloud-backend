package com.frame.template.service.basic.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
    "com.frame.template.service.basic.domain.entity",
})
@EnableJpaRepositories(basePackages = {
    "com.frame.template.service.basic.repository",
})

@Import({MusicianServiceConfiguration.class})
public class MusicianConfiguration {
}
