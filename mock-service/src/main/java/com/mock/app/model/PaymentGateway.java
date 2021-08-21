package com.mock.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

import static com.mock.app.model.Endpoint.PaymentEndpoint.*;
import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.PAYMENT_GATEWAY_ROOT;
import static com.mock.app.model.Endpoint.PaymentGatewayEndpoint.RECEIVE;


public enum PaymentGateway {
    GATEWAY1(PAYMENT_ROOT + SUCCESS), GATEWAY2(PAYMENT_ROOT + FAILURE);

    private String callbackUrl;
    private String serverPort;
    PaymentGateway(final String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getUrl() {
        return "http://localhost:" + this.serverPort + PAYMENT_GATEWAY_ROOT + RECEIVE;
    }

    public String getCallbackUrl() {
        return "http://localhost:" + this.serverPort + this.callbackUrl;
    }

    @Component
    public static class PaymentGatewayValues {
        private InjectedProps injectedProps;
        @Autowired
        PaymentGatewayValues(final InjectedProps injectedProps) {
            this.injectedProps = injectedProps;
        }

        @PostConstruct
        public void setServerPort() {
            for (PaymentGateway paymentGateway : EnumSet.allOf(PaymentGateway.class)) {
                paymentGateway.serverPort = this.injectedProps.getServerPort();
            }
        }
    }
}
