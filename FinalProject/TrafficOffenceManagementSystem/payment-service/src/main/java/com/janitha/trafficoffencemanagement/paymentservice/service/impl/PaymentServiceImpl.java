package com.janitha.trafficoffencemanagement.paymentservice.service.impl;

import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.paymentservice.config.Token;
import com.janitha.trafficoffencemanagement.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public BigDecimal calculatePayment(String licenseNo) throws RestClientException, NullPointerException {

        HttpHeaders headers = new HttpHeaders();
        //passing auth token with header to call fines service since fines service is already secured
        headers.add("Authorization", Token.getAccessToken());
        HttpEntity entity = new HttpEntity<>(headers);



       // JSONParser jsonParser = new JSONParser();
//no instance for localhost occured because @LoadBalance annotation

        // Multiple objects also retrieve
   //     FineResponse fineResponse = restTemplate.getForObject("http://localhost:49209/services/fines/"+licenseNo,FineResponse.class);

        //getting unpaid offences ids from offence database
    //    List unpaidOffences = restTemplate.getForObject("http://OFFENCE/services/fines/"+licenseNo+"/status/",List.class);
   //     List unpaidOffences = restTemplate.getForObject("http://localhost:8191/services/fines/"+licenseNo+"/status/",List.class);
        List unpaidOffences = restTemplate.exchange("http://offence/services/fines/"+licenseNo+"/status/", HttpMethod.GET, entity, List.class).getBody();


        if(unpaidOffences!=null){

            List<BigDecimal> finesForOffence = new ArrayList<>();
            BigDecimal sum = new BigDecimal(0);

            //getting appropriate fine rates and get sum for offence ids
            for(int i=0; i<unpaidOffences.size(); i++){
        //        Offence offence = restTemplate.getForObject("http://OFFENCE/services/offences/"+ unpaidOffences.get(i), Offence.class);
         //       Offence offence = restTemplate.getForObject("http://localhost:8191/services/offences/"+ unpaidOffences.get(i), Offence.class);
                Offence offence = restTemplate.exchange("http://offence/services/offences/"+ unpaidOffences.get(i), HttpMethod.GET, entity, Offence.class).getBody();

                sum = sum.add(offence.getFine());
            }

            return sum;

        }
        else{
            return new BigDecimal(0);
        }
    }
}
