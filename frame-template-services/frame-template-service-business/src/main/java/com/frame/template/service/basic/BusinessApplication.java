package com.frame.template.service.basic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Hello world!
 *
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
    "com.frame.template.service.basic.domain.entity",
    "com.frame.template.service.basic.domain.service",
})
@EnableJpaRepositories(basePackages = {
    "com.frame.template.service.basic.repository",
})
@SpringBootApplication
public class BusinessApplication
{
    public static void main(String[] args) {

        SpringApplication.run(BusinessApplication.class, args);
    }
}
