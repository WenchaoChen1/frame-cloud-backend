package com.frame.template.gateway;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
//@Import(value = EnableAutoConfigurationImportSelector.class)
public class GstdevGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GstdevGatewayApplication.class, args);
    }

}
