// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.gstdev.template.service.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@ComponentScan(value = {"com.gstdev.cloud.commons.utils",
  "com.gstdev.template.service.email",
  "com.gstdev.template.common.redis"})
@EntityScan(value = {"com.gstdev.template.service.email"})
@EnableJpaRepositories(value = {"com.gstdev.template.service.email"})
@ConfigurationPropertiesScan("com.gstdev.template.service.email")
@SpringBootApplication
public class EmailApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmailApplication.class, args);
  }

}
