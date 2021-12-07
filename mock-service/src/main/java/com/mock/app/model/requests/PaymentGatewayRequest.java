package com.mock.app.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentGatewayRequest {
    private String description;
    private String method;
    private String currency;
    private Float amount;
    private String gateway;
    private String callbackUrl;
    private Long userId;
}
