package com.mock.app.model.responses;

import com.mock.app.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class ProductInventoryResponse {
    private Product product;
    private Long requestedByUser;
}
