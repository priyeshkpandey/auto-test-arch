package com.mock.docker.app.model.responses;

import com.mock.docker.app.model.Product;
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
