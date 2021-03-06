package com.mock.app.model;

import com.mock.app.model.entities.ProductTable;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private Float price;
    private Float discount;

    public void cloneFromProductTable(final ProductTable productTable) {
        this.setId(productTable.getId());
        this.setProductName(productTable.getProductName());
        this.setProductDescription(productTable.getProductDescription());
        this.setPrice(productTable.getPrice());
        this.setDiscount(productTable.getDiscount());
    }
}
