package com.mock.docker.app.controller;

import com.mock.docker.app.model.PaymentGateway;
import com.mock.docker.app.model.PaymentInfo;
import com.mock.docker.app.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import static com.util.constant.Endpoint.PathVariable.PAYMENT_GATEWAY;
import static com.util.constant.Endpoint.PathVariable.USER_ID;
import static com.util.constant.Endpoint.PaymentEndpoint.*;

@RestController(PAYMENT_ROOT)
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = SEND)
    public void sendPaymentToGateway(final @PathVariable(PAYMENT_GATEWAY) String paymentGateway,
                                     final @RequestBody PaymentInfo paymentInfo) {
        this.paymentService.sendPaymentToGateway(PaymentGateway.valueOf(paymentGateway.toUpperCase()), paymentInfo);
    }

    @RequestMapping(method = RequestMethod.POST, value = SUCCESS)
    public void receiveSuccessPaymentResponse(final @Param(USER_ID) String userId) {
        this.paymentService.receiveSuccessPaymentResponse(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = FAILURE)
    public void receiveFailedPaymentResponse(final @Param(USER_ID) String userId) {
        this.paymentService.receiveFailedPaymentResponse(userId);
    }

}
