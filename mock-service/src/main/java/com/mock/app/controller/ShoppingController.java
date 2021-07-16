package com.mock.app.controller;

import com.mock.app.model.Product;
import com.mock.app.model.User;
import com.mock.app.model.responses.OrderConfirmationResponse;
import com.mock.app.model.responses.OrderSummaryResponse;
import com.mock.app.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mock.app.model.Endpoint.PathVariable.USER_ID;
import static com.mock.app.model.Endpoint.QueryParam.QUERY;
import static com.mock.app.model.Endpoint.ShoppingEndpoint.*;

@RestController(SHOPPING_ROOT)
public class ShoppingController {
    private ShoppingService shoppingService;

    @Autowired
    public ShoppingController(final ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @RequestMapping(method = RequestMethod.GET, value = SEARCH)
    public List<Product> searchProducts(final @RequestParam(QUERY) String query) {
        return this.shoppingService.searchProducts(query);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_CART)
    public void addProductsInCart(final @PathVariable(USER_ID) Long userId, final @RequestBody List<Product> products) {
        final User user = new User();
        user.setId(userId);
        this.shoppingService.addProductsInCart(user, products);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_CHECKOUT)
    public OrderSummaryResponse checkoutCart(final @PathVariable(USER_ID) Long userId) {
        final User user = new User();
        user.setId(userId);
        return this.shoppingService.checkoutCart(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_PAYMENT)
    public void initiatePayment(final @PathVariable(USER_ID) Long userId) {
        final User user = new User();
        user.setId(userId);
        this.shoppingService.initiatePayment(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_ORDER)
    public OrderConfirmationResponse confirmOrder(final @PathVariable(USER_ID) Long userId) {
        final User user = new User();
        user.setId(userId);
        return this.shoppingService.confirmOrder(user);
    }
}
