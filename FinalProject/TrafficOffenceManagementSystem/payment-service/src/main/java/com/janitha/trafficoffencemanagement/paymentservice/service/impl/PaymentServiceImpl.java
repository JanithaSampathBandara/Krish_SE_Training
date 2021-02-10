package com.janitha.trafficoffencemanagement.paymentservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.paymentservice.dto.FineResponse;
import com.janitha.trafficoffencemanagement.paymentservice.service.PaymentService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public BigDecimal calculatePayment(String licenseNo) {
       // JSONParser jsonParser = new JSONParser();
//no instance for lclhost occured because @LoadBalance annotation
        //    Driver driver = restTemplate.getForObject("http://localhost:DRIVER/services/drivers/"+fine.getLicenseNo(),Driver.class);
    //    Driver driver = restTemplate.getForObject("http://localhost:64267/services/drivers/"+fine.getLicenseNo(),Driver.class);
        System.out.println("dafrerferf");
        // Multiple objects also retrieve
   //     FineResponse fineResponse = restTemplate.getForObject("http://localhost:49209/services/fines/"+licenseNo,FineResponse.class);

        //getting unpaid offences ids from offence database
     //   List unpaidOffences = restTemplate.getForObject("http://OFFENCE/services/fines/"+licenseNo+"/status/",List.class);
      List unpaidOffences = restTemplate.getForObject("http://localhost:51889/services/fines/"+licenseNo+"/status/",List.class);

        if(unpaidOffences!=null){

            List<BigDecimal> finesForOffence = new ArrayList<>();
            BigDecimal sum = new BigDecimal(0);

            //getting appropriate fine rates and get sum for offence ids
            for(int i=0; i<unpaidOffences.size(); i++){
         //       Offence offence = restTemplate.getForObject("http://OFFENCE/services/offences/"+ unpaidOffences.get(i), Offence.class);
              Offence offence = restTemplate.getForObject("http://localhost:51889/services/offences/"+ unpaidOffences.get(i), Offence.class);
                sum = sum.add(offence.getFine());
            }

            return sum;

        }
        else{
            return new BigDecimal(0);
        }
    }
}
