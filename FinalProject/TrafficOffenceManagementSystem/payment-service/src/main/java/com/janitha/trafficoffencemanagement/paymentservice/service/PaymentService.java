package com.janitha.trafficoffencemanagement.paymentservice.service;

import com.janitha.trafficoffencemanagement.paymentservice.dto.FineResponse;

import java.math.BigDecimal;

public interface PaymentService {

    public BigDecimal calculatePayment(String licenseNo);

}
