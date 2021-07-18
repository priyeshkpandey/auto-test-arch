package com.mock.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private Float price;
    private Float discount;
}
