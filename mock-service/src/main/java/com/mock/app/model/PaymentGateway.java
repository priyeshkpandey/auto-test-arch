package com.mock.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.PAYMENT_GATEWAY_ROOT;
import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.RECEIVE;

@Component
public enum PaymentGateway {
    GATEWAY1, GATEWAY2;
    @Autowired
    private InjectedProps injectedProps;

    public String getUrl() {
        return "http://localhost:" + injectedProps.getServerPort() + PAYMENT_GATEWAY_ROOT + RECEIVE;
    }
}
