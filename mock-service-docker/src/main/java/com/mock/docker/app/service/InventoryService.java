package com.mock.docker.app.service;

import com.mock.docker.app.model.Product;
import com.mock.docker.app.model.User;
import com.mock.docker.app.model.exceptions.InsufficientPermissionsException;
import com.mock.docker.app.model.exceptions.NoSuchProductException;
import com.mock.docker.app.model.responses.ProductInventoryResponse;

public interface InventoryService {
    public void addProduct(final User user, final Product product) throws InsufficientPermissionsException;
    public void deleteProduct(final User user, final Product product) throws InsufficientPermissionsException;
    public ProductInventoryResponse getProductInventory(final User user, final Product product) throws InsufficientPermissionsException, NoSuchProductException;
}
