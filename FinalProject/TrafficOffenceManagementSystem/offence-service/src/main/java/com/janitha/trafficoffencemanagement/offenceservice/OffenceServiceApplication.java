package com.janitha.trafficoffencemanagement.offenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.janitha.trafficoffencemanagement.model.offenceservice")
public class OffenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OffenceServiceApplication.class, args);
    }

}
