package com.mock.app.model.responses;

import com.mock.app.model.Product;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInventoryResponse {
    private Product product;
    private Long requestedByUser;
}
