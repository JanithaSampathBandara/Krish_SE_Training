/*package com.janitha.trafficoffencemanagement.offenceservice.service;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.offenceservice.config.Token;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DriverInfoService {

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackDriverInfo")
    public Response getDriverInfo(Fine fine){
        System.out.println("inside hystrix");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", Token.getAccessToken());
        HttpEntity entity = new HttpEntity<>(headers);
        System.out.println("license" + fine.getLicenseNo());
        Response driver = restTemplate.exchange("http://driver/services/drivers/"+fine.getLicenseNo(), HttpMethod.GET, entity, Response.class).getBody();
        System.out.println("license" +fine.getLicenseNo());
        return driver;
    }

    public Response getFallbackDriverInfo(Fine fine){
        System.out.println("inside fallback");
        System.out.println("fineid" +fine.getFineId());
        Response response = new Response();
        response.setStatusCode(-99);
        response.setMessage("Service Down");
        response.setData(null);

        return response;
    }
}
*/