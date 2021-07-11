package com.mock.app.service;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.ProductInventoryResponse;

public interface InventoryService {
    public void addProduct(final User user, final Product product);
    public void deleteProduct(final User user, final Product product);
    public ProductInventoryResponse getProductInventory(final User user, final Product product);
}
