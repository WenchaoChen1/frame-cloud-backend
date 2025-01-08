package com.frame.template.service.basic;

import com.frame.template.autoconfigure.service.system.configuration.AutoSystemConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import({AutoSystemConfiguration.class})
public class BusinessApplication
{
    public static void main(String[] args) {

        SpringApplication.run(BusinessApplication.class, args);
    }
}
