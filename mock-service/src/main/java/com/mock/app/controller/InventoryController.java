package com.mock.app.controller;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.exceptions.InsufficientPermissionsException;
import com.mock.app.model.exceptions.NoSuchProductException;
import com.mock.app.model.responses.ProductInventoryResponse;
import com.mock.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mock.app.model.Endpoint.InventoryEndpoint.*;
import static com.mock.app.model.Endpoint.PathVariable.PRODUCT_ID;
import static com.mock.app.model.Endpoint.PathVariable.USER_ID;

@RestController(INVENTORY_ROOT)
public class InventoryController {
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_PRODUCT)
    public void addProduct(final @PathVariable(USER_ID) Long userId, final @PathVariable(PRODUCT_ID) Long productId) throws InsufficientPermissionsException {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        this.inventoryService.addProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = USER_PRODUCT)
    public void deleteProduct(final @PathVariable(USER_ID) Long userId, final @PathVariable(PRODUCT_ID) Long productId) throws InsufficientPermissionsException {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        this.inventoryService.deleteProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.GET, value = USER_PRODUCT)
    public ProductInventoryResponse getProductInventory(final @PathVariable(USER_ID) Long userId, final @PathVariable(PRODUCT_ID) Long productId) throws InsufficientPermissionsException, NoSuchProductException {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        return this.inventoryService.getProductInventory(user, product);
    }
}
