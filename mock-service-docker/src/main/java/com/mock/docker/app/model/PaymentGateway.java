package com.mock.docker.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

import static com.util.constant.Endpoint.PaymentEndpoint.*;
import static com.util.constant.Endpoint.PaymentGatewayEndpoint.PAYMENT_GATEWAY_ROOT;
import static com.util.constant.Endpoint.PaymentGatewayEndpoint.RECEIVE;


public enum PaymentGateway {
    GATEWAY1(PAYMENT_ROOT + SUCCESS), GATEWAY2(PAYMENT_ROOT + FAILURE);

    private String callbackUrl;
    private String serverPort;
    private String serverUrl;
    PaymentGateway(final String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getUrl() {
        return this.serverUrl + ":" + this.serverPort + PAYMENT_GATEWAY_ROOT + RECEIVE;
    }

    public String getCallbackUrl() {
        return this.serverUrl + ":" + this.serverPort + this.callbackUrl;
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
                paymentGateway.serverUrl = this.injectedProps.getServerHostUrl();
            }
        }
    }
}
