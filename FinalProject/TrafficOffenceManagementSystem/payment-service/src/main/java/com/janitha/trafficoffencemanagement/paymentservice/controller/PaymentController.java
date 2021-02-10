package com.janitha.trafficoffencemanagement.paymentservice.controller;

import com.janitha.trafficoffencemanagement.paymentservice.dto.FineResponse;
import com.janitha.trafficoffencemanagement.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value="services/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(value="{licenseNo}")
    public BigDecimal calculatePayment(@PathVariable String licenseNo){
        return paymentService.calculatePayment(licenseNo);
       // return 10.5;
    }

}
