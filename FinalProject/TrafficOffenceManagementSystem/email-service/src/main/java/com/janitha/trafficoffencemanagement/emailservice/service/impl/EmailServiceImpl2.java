package com.janitha.trafficoffencemanagement.emailservice.service.impl;
/*
import com.janitha.trafficoffencemanagement.emailservice.service.EmailService;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmailServiceImpl2 {

    EmailServiceImpl esi = new EmailServiceImpl();

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 5000)
    public void sendEmail(){
        String token = esi.sendReminderEmails();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + token);
        HttpEntity entity = new HttpEntity<>(headers);


        ParameterizedTypeReference<List<Fine>> reference = new ParameterizedTypeReference<List<Fine>>() {};
        List<Fine> unpaidFines =  restTemplate.exchange("http://offence/services/fines/unpaid", HttpMethod.GET, entity, reference).getBody();
        System.out.println(unpaidFines);

        System.out.println("janzz");
    }

}
*/