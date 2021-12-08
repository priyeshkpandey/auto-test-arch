package com.mock.app.model.entities;

import com.mock.app.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
public class ProductTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    public void cloneFromProduct(final Product product) {
        this.setProductName(product.getProductName());
        this.setProductDescription(product.getProductDescription());
        this.setPrice(product.getPrice());
        this.setDiscount(product.getDiscount());
    }
}
