package com.mock.docker.app.controller;

import com.mock.docker.app.model.Product;
import com.mock.docker.app.model.User;
import com.mock.docker.app.model.exceptions.InsufficientPermissionsException;
import com.mock.docker.app.model.exceptions.NoSuchProductException;
import com.mock.docker.app.model.responses.ProductInventoryResponse;
import com.mock.docker.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mock.docker.app.model.Endpoint.InventoryEndpoint.*;
import static com.mock.docker.app.model.Endpoint.PathVariable.PRODUCT_ID;
import static com.mock.docker.app.model.Endpoint.PathVariable.USER_ID;

@RestController(INVENTORY_ROOT)
public class InventoryController {
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_PRODUCTS)
    public void addProduct(final @PathVariable(USER_ID) Long userId, final @RequestBody Product product) throws InsufficientPermissionsException {
        final User user = new User();
        user.setId(userId);
        this.inventoryService.addProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = USER_PRODUCTS)
    public void deleteProduct(final @PathVariable(USER_ID) Long userId, final @PathVariable(PRODUCT_ID) Long productId) throws InsufficientPermissionsException {
        final User user = new User();
        user.setId(userId);
        final Product product = new Product();
        product.setId(productId);
        this.inventoryService.deleteProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.GET, value = USER_PRODUCT)
    public ProductInventoryResponse getProductInventory(final @PathVariable(USER_ID) Long userId, final @PathVariable(PRODUCT_ID) Long productId) throws InsufficientPermissionsException, NoSuchProductException {
        final User user = new User();
        user.setId(userId);
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        return this.inventoryService.getProductInventory(user, product);
    }
}
