package com.mock.app.service.impl;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.entities.ProductTable;
import com.mock.app.model.responses.OrderConfirmationResponse;
import com.mock.app.model.responses.OrderSummaryResponse;
import com.mock.app.repositories.ProductRepository;
import com.mock.app.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShoppingServiceImpl implements ShoppingService {
    private static final Map<Long, List<Product>> USER_CART = new HashMap<>();

    private ProductRepository productRepository;

    @Autowired
    public ShoppingServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        if (null == USER_CART.get(user.getId())) {
            USER_CART.put(user.getId(), products);
        } else {
            USER_CART.get(user.getId()).addAll(products);
        }
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
