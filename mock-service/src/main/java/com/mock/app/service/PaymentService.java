package com.mock.app.service;

import com.mock.app.model.PaymentGateway;
import com.mock.app.model.PaymentInfo;

public interface PaymentService {
    public void sendPaymentToGateway(final PaymentGateway paymentGateway, final PaymentInfo paymentInfo);
    public void receiveSuccessPaymentResponse();
    public void receiveFailedPaymentResponse();
}
