// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.system;

import com.gstdev.cloud.service.system.configuration.FrameSystemConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@ComponentScan(value = {
    "com.gstdev.cloud",
    "com.gstdev.cloud.service.system",
    "com.gstdev.cloud.springframework.openfeign",
    "com.gstdev.cloud.cache.jetcache",
    "com.gstdev.cloud.oauth2",
    "com.gstdev.cloud.rest.autoconfigure",
    "com.frame"})
//@EntityScan(value = {"com.frame.template"})
//@EnableJpaRepositories(value = {"com.frame"})
@ConfigurationPropertiesScan("com.frame")
@EntityScan(value = {"com.frame.template.service.system"})
//@EnableJpaRepositories(value = {"com.frame.template.service.system"})
@SpringBootApplication
@Import({FrameSystemConfiguration.class})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
