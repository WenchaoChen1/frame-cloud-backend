package com.frame.template.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@Import(value = EnableAutoConfigurationImportSelector.class)
public class GstdevGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GstdevGatewayApplication.class, args);
    }

}
