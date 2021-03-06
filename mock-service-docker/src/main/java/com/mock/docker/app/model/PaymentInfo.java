package com.mock.docker.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentInfo {
    private String method;
    private String currency;
    private Float amount;
    private Long userId;
}
