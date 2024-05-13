// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.identity;


import com.frame.template.service.identity.configuration.IdentityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableFeignClients
//@ComponentScan(value = {
//    "com.frame.template.common.redis.currentLoginInformation",
//    "com.frame.template",
//    "com.gstdev.cloud.rest.autoconfigure",
//    "com.gstdev.cloud",
//})
//@EntityScan(value = {"com.frame.template.service.identity"})
//@EnableJpaRepositories(value = {"com.frame.template.service.identity"})
@SpringBootApplication
//@EnableFrameJetCache

@Import(IdentityConfiguration.class)
public class IdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

}
