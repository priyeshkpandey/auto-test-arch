package com.mock.app.service;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.exceptions.InsufficientPermissionsException;
import com.mock.app.model.exceptions.NoSuchProductException;
import com.mock.app.model.responses.ProductInventoryResponse;

public interface InventoryService {
    public void addProduct(final User user, final Product product) throws InsufficientPermissionsException;
    public void deleteProduct(final User user, final Product product) throws InsufficientPermissionsException;
    public ProductInventoryResponse getProductInventory(final User user, final Product product) throws InsufficientPermissionsException, NoSuchProductException;
}
