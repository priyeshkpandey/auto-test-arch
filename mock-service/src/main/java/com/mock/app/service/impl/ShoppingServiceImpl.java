package com.mock.app.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.entities.OrderTable;
import com.mock.app.model.entities.ProductTable;
import com.mock.app.model.responses.OrderConfirmationResponse;
import com.mock.app.model.responses.OrderSummaryResponse;
import com.mock.app.repositories.OrderRepository;
import com.mock.app.repositories.ProductRepository;
import com.mock.app.service.ShoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShoppingServiceImpl implements ShoppingService {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingServiceImpl.class);
    private static final Map<Long, List<Product>> USER_CART = new HashMap<>();

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public ShoppingServiceImpl(final ProductRepository productRepository,
                               final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public List<Product> searchProducts(String query) {
        final List<ProductTable> productTableListByName = this.productRepository.findAllByProductNameContaining(query);
        final List<ProductTable> productTableListByDesc = this.productRepository.findAllByProductDescriptionContaining(query);
        final List<ProductTable> combinedProductTableList = new ArrayList<>();
        combinedProductTableList.addAll(productTableListByName);
        combinedProductTableList.addAll(productTableListByDesc);
        final List<Product> products = new ArrayList<>();
        if (!combinedProductTableList.isEmpty()) {
            for (ProductTable productTable : combinedProductTableList) {
                final Product product = new Product();
                product.cloneFromProductTable(productTable);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public void addProductsInCart(User user, List<Product> products) {
        final List<Product> cartProducts = USER_CART.get(user.getId());
        if (null == cartProducts) {
            USER_CART.put(user.getId(), products);
        } else {
            cartProducts.addAll(products);
        }
    }

    @Override
    public OrderSummaryResponse checkoutCart(User user) {
        final List<Product> cartProducts = USER_CART.get(user.getId());
        final OrderSummaryResponse orderSummaryResponse = new OrderSummaryResponse();
        orderSummaryResponse.setProducts(cartProducts);
        Float totalPrice = 0f;
        for (Product product : cartProducts) {
            totalPrice += (product.getPrice() - product.getDiscount());
        }
        orderSummaryResponse.setTotalPrice(totalPrice);
        return orderSummaryResponse;
    }

    @Override
    public void initiatePayment(User user) {
        final OrderSummaryResponse orderSummaryResponse = this.checkoutCart(user);
        final OrderTable order = new OrderTable();
        order.setUserId(user.getId());
        order.setPaymentStatus(1);
        try {
            order.setProductsJson(this.objectMapper.writeValueAsString(orderSummaryResponse.getProducts()));
            USER_CART.remove(user.getId());
            LOG.debug("Successfully saved the order for the payment initialization");
        } catch (JsonProcessingException e) {
            LOG.error("Exception saving the order while payment initialization", e);
        }
    }

    @Override
    public Boolean orderPaymentStatus(User user, Long orderId) {
        final List<OrderTable> userOrders = this.orderRepository.findOrderTableByUserId(user.getId());
        for (OrderTable order : userOrders) {
            if (order.getId().equals(orderId)) {
                return (order.getPaymentStatus().equals(0));
            }
        }
        return false;
    }

}
