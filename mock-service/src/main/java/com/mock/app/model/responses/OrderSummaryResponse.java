package com.mock.app.model.responses;

import com.mock.app.model.Product;
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
