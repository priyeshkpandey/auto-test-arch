package com.mock.app.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "payment_status")
    private Integer paymentStatus;
    @Column(name = "products_json")
    private String productsJson;
    @Column(name = "total_price")
    private Float totalPrice;
}
