package com.mock.docker.app.service;

import com.mock.docker.app.model.requests.PaymentGatewayRequest;

public interface PaymentGatewayService {
    public void receivePaymentRequest(final PaymentGatewayRequest paymentGatewayRequest);
}
