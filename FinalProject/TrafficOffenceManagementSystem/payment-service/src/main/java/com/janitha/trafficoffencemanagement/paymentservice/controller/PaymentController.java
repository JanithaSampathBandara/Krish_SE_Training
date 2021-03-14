package com.janitha.trafficoffencemanagement.paymentservice.controller;

import com.janitha.trafficoffencemanagement.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="services/payments")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    PaymentService paymentService;

    @GetMapping(value="{licenseNo}")
    public BigDecimal calculatePayment(@PathVariable String licenseNo){

        try{

            return paymentService.calculatePayment(licenseNo);

        }catch(RestClientException restClientException){

            logger.error(restClientException.getMessage());
            return new BigDecimal(99).negate();

        }catch(NullPointerException nullPointerException){

            logger.error(nullPointerException.getMessage());
            return new BigDecimal(99).negate();
        }
        catch(Exception exception){
            logger.error(exception.getMessage());
            return new BigDecimal(99).negate();
        }

       // return 10.5;
    }

}
