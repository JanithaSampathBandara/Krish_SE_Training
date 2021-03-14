package com.janitha.trafficoffencemanagement.offenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EntityScan(basePackages = "com.janitha.trafficoffencemanagement.model.offenceservice")
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableResourceServer
//@EnableCircuitBreaker
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OffenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OffenceServiceApplication.class, args);
    }

}
