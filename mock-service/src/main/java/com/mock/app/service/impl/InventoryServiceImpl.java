package com.mock.app.service.impl;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.ProductInventoryResponse;
import com.mock.app.service.InventoryService;
import org.springframework.stereotype.Component;

@Component
public class InventoryServiceImpl implements InventoryService {
    @Override
    public void addProduct(User user, Product product) {

    }

    @Override
    public void deleteProduct(User user, Product product) {

    }

    @Override
    public ProductInventoryResponse getProductInventory(User user, Product product) {
        return null;
    }
}
