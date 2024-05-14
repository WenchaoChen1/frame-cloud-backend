// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Cloud <support@gstdev.com>
// Copyright (c) 2022-2025 gstdev.com
//
// ====================================================

package com.frame.template.service.email;

import com.gstdev.cloud.starter.ouath2.resource.server.configuration.ResourceServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@EnableFeignClients
@ComponentScan(value = {
    "com.gstdev.cloud.rest.autoconfigure",
    "com.frame.template.service.email.service",
    "com.frame.template.service.email.controller"})
@EntityScan(value = {"com.frame.template.service.email.pojo.entity"})
@EnableJpaRepositories(value = {"com.frame.template.service.email.repository"})
@SpringBootApplication
@Import({ResourceServerAutoConfiguration.class})
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
