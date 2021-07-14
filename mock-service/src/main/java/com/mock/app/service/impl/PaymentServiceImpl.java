package com.mock.app.service.impl;

import com.mock.app.model.PaymentGateway;
import com.mock.app.model.PaymentInfo;
import com.mock.app.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void sendPaymentToGateway(PaymentGateway paymentGateway, PaymentInfo paymentInfo) {

    }

    @Override
    public void receiveSuccessPaymentResponse() {

    }

    @Override
    public void receiveFailedPaymentResponse() {

    }
}
