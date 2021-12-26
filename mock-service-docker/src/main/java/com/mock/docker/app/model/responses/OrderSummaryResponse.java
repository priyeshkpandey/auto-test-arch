package com.mock.docker.app.model.responses;

import com.mock.docker.app.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrderSummaryResponse {
    private List<Product> products;
    private Float totalPrice;
}
