package com.mock.app.service.impl;

import com.api.client.auth.AuthFactoryBuilder;
import com.api.client.contract.*;
import com.api.client.factory.APIClientFactoryBuilder;
import com.api.client.model.request.GenericAPIRequest;
import com.api.client.model.request.GenericAPIRequestBody;
import com.mock.app.model.requests.PaymentGatewayRequest;
import com.mock.app.service.PaymentGatewayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentGatewayServiceImpl.class);
    @Override
    public void receivePaymentRequest(PaymentGatewayRequest paymentGatewayRequest) {
        final APIClient client = APIClientFactoryBuilder.getRestAssuredAPIClientFactory().get();
        final APIRequestBody requestBody = GenericAPIRequestBody.builder().type(RequestBodyType.NONE).build();
        final APIRequest request = GenericAPIRequest.builder().auth(AuthFactoryBuilder.getNoneAuthFactory().get())
                .httpMethod(HttpMethod.POST).URL(paymentGatewayRequest.getCallbackUrl()).body(requestBody).build();
        final APIResponse response = client.execute(request);
        if (response.getStatusCode().equalsIgnoreCase("200")) {
            LOG.info("Payment callback sent successfully");
        } else {
            LOG.error("Payment callback failed");
        }
    }
}
