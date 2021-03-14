package com.janitha.trafficoffencemanagement.paymentservice.service;

import java.math.BigDecimal;

public interface PaymentService {

    public BigDecimal calculatePayment(String licenseNo);

}
