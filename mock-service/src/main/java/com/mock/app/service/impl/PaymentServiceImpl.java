package com.mock.app.service.impl;

import com.api.client.auth.AuthFactoryBuilder;
import com.api.client.contract.*;
import com.api.client.factory.APIClientFactoryBuilder;
import com.api.client.model.request.GenericAPIRequest;
import com.api.client.model.request.GenericAPIRequestBody;
import com.mock.app.model.PaymentGateway;
import com.mock.app.model.PaymentInfo;
import com.mock.app.model.requests.PaymentGatewayRequest;
import com.mock.app.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Override
    public void sendPaymentToGateway(PaymentGateway paymentGateway, PaymentInfo paymentInfo) {
        final APIClient client = APIClientFactoryBuilder.getRestAssuredAPIClientFactory().get();
        final APIRequestBody requestBody = GenericAPIRequestBody.builder().type(RequestBodyType.OBJECT)
                .object(buildPaymentGatewayRequest(paymentGateway, paymentInfo)).build();
        final APIRequest request = GenericAPIRequest.builder().auth(AuthFactoryBuilder.getNoneAuthFactory().get())
                .httpMethod(HttpMethod.POST).URL(paymentGateway.getUrl()).body(requestBody).build();
        final APIResponse response = client.execute(request);
        if (response.getStatusCode().equalsIgnoreCase("200")) {
            LOG.info("Payment request successfully sent to the gateway {}", paymentGateway.name());
        } else {
            LOG.error("Payment request failed");
        }
    }

    private PaymentGatewayRequest buildPaymentGatewayRequest(final PaymentGateway paymentGateway, final PaymentInfo paymentInfo) {
        final PaymentGatewayRequest paymentGatewayRequest = new PaymentGatewayRequest();
        paymentGatewayRequest.setGateway(paymentGateway.name());
        paymentGatewayRequest.setMethod(paymentInfo.getMethod());
        paymentGatewayRequest.setCurrency(paymentInfo.getCurrency());
        paymentGatewayRequest.setAmount(paymentInfo.getAmount());
        paymentGatewayRequest.setDescription("Going to payment gateway " + paymentGateway.name());
        paymentGatewayRequest.setCallbackUrl(paymentGateway.getCallbackUrl());
        return paymentGatewayRequest;
    }

    @Override
    public void receiveSuccessPaymentResponse() {
        LOG.info("Payment successfully completed");
    }

    @Override
    public void receiveFailedPaymentResponse() {
        LOG.error("Payment failed");
    }
}
