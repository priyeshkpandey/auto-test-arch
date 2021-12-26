package com.mock.docker.app.service;

import com.mock.docker.app.model.Product;
import com.mock.docker.app.model.User;
import com.mock.docker.app.model.responses.OrderSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingService {
    public List<Product> searchProducts(final String query);
    public void addProductsInCart(final User user, final List<Product> products);
    public OrderSummaryResponse checkoutCart(final User user);
    public void initiatePayment(final User user);
    public Boolean orderPaymentStatus(final User user, final Long orderId);
}
