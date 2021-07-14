package com.mock.app.controller;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.ProductInventoryResponse;
import com.mock.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void addProduct(final @RequestParam(USER_ID) Long userId, final @RequestParam(PRODUCT_ID) Long productId) {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        this.inventoryService.addProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = USER_PRODUCT)
    public void deleteProduct(final @RequestParam(USER_ID) Long userId, final @RequestParam(PRODUCT_ID) Long productId) {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        this.inventoryService.deleteProduct(user, product);
    }

    @RequestMapping(method = RequestMethod.GET, value = USER_PRODUCT)
    public ProductInventoryResponse getProductInventory(final @RequestParam(USER_ID) Long userId, final @RequestParam(PRODUCT_ID) Long productId) {
        final User user = new User();
        final Product product = new Product();
        user.setId(userId);
        product.setId(productId);
        return this.inventoryService.getProductInventory(user, product);
    }
}
