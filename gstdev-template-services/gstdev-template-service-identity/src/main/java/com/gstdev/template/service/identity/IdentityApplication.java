// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@ComponentScan(value = {"com.gstdev"})
@EntityScan(value = {"com.gstdev.cloud.oauth2.server.authorization.domain", "com.gstdev.template.service.identity"})
@EnableJpaRepositories(value = {"com.gstdev.cloud.oauth2.server.authorization.repository", "com.gstdev.template.service.identity"})
@ConfigurationPropertiesScan("com.gstdev")
@SpringBootApplication
public class IdentityApplication {

  public static void main(String[] args) {
    SpringApplication.run(IdentityApplication.class, args);
  }

}
