package com.mock.app.service;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.OrderConfirmationResponse;
import com.mock.app.model.responses.OrderSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingService {
    public List<Product> searchProducts(final String query);
    public void addProductsInCart(final User user, final List<Product> products);
    public OrderSummaryResponse checkoutCart(final User user);
    public void initiatePayment(final User user);
    public OrderConfirmationResponse confirmOrder(final User user);
    public OrderConfirmationResponse failOrder(final User user);
}
