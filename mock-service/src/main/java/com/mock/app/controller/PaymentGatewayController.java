package com.mock.app.controller;

import com.mock.app.model.requests.PaymentGatewayRequest;
import com.mock.app.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.PAYMENT_GATEWAY_ROOT;
import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.RECEIVE;

@RestController(PAYMENT_GATEWAY_ROOT)
public class PaymentGatewayController {
    private PaymentGatewayService paymentGatewayService;

    @Autowired
    public void PaymentGatewayController(final PaymentGatewayService paymentGatewayService) {
        this.paymentGatewayService = paymentGatewayService;
    }

    @RequestMapping(method = RequestMethod.POST, value = RECEIVE)
    public void receivePaymentRequest(final @RequestBody PaymentGatewayRequest paymentGatewayRequest) {
        this.paymentGatewayService.receivePaymentRequest(paymentGatewayRequest);
    }
}
