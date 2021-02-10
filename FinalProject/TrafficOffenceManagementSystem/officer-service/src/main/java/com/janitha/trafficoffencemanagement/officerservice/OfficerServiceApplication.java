package com.janitha.trafficoffencemanagement.officerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.janitha.trafficoffencemanagement.model.officerservice")
public class OfficerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficerServiceApplication.class, args);
    }

}
