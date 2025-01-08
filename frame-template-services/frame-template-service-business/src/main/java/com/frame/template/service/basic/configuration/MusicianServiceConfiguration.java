package com.frame.template.service.basic.configuration;

import com.frame.template.service.basic.repository.MusicianRepository;
import com.frame.template.service.basic.service.MusicianService;
import com.frame.template.service.basic.service.MusicianServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:  模块配置 </p>
*/
@Configuration(proxyBeanMethods = false)
public class MusicianServiceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MusicianServiceConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public MusicianService musicianService(MusicianRepository musicianRepository) {
        return new MusicianServiceImpl(musicianRepository);
    }

}

