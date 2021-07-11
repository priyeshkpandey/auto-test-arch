package com.mock.app.service.impl;

import com.mock.app.model.requests.PaymentGatewayRequest;
import com.mock.app.service.PaymentGatewayService;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
    @Override
    public void receivePaymentRequest(PaymentGatewayRequest paymentGatewayRequest) {

    }
}
