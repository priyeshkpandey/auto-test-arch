package com.mock.app.service.impl;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.OrderConfirmationResponse;
import com.mock.app.model.responses.OrderSummaryResponse;
import com.mock.app.service.ShoppingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingServiceImpl implements ShoppingService {
    @Override
    public List<Product> searchProducts(String query) {
        return null;
    }

    @Override
    public void addProductsInCart(User user, List<Product> products) {

    }

    @Override
    public OrderSummaryResponse checkoutCart(User user) {
        return null;
    }

    @Override
    public void initiatePayment(User user) {

    }

    @Override
    public OrderConfirmationResponse confirmOrder(User user) {
        return null;
    }
}
