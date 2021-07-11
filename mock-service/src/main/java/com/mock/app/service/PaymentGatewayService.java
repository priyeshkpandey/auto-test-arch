package com.mock.app.service;

import com.mock.app.model.requests.PaymentGatewayRequest;

public interface PaymentGatewayService {
    public void receivePaymentRequest(final PaymentGatewayRequest paymentGatewayRequest);
}
