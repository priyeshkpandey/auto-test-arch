package com.mock.app.model;

import static com.mock.app.model.Endpoint.Entity.USER;

public final class Endpoint {
    private Endpoint() {}
    public static final String ROOT = "/app";
    public static final class PathVariable {
        private PathVariable() {}
        public static final String USER_ID = "userId";
        public static final String PRODUCT_ID = "productId";
        public static final String PAYMENT_GATEWAY = "paymentGateway";
        public static final String USER_ID_PATH = "{" + USER_ID + "}";
        public static final String PRODUCT_ID_PATH = "{" + PRODUCT_ID + "}";
        public static final String PAYMENT_GATEWAY_PATH = "{" + PAYMENT_GATEWAY + "}";
    }

    public static final class Entity {
        public static final String PRODUCT = "/product";
        public static final String USER = "/user";
    }
    public static final class UserEndpoint {

    }
    public static final class InventoryEndpoint {
        private InventoryEndpoint() {}
        public static final String INVENTORY_ROOT = ROOT + "/inv";

        public static final String USER_PRODUCT = USER + "/" + PathVariable.USER_ID_PATH
                + Entity.PRODUCT + "/" + PathVariable.PRODUCT_ID_PATH;
    }

    public static final class PaymentGatewayEndpoint {
        private PaymentGatewayEndpoint() {}
        public static final String PAYMENT_GATEWAY_ROOT = ROOT + "/payg";
        public static final String RECEIVE = "/receive";
    }

    public static final class PaymentEndpoint {
        public static final String PAYMENT_ROOT = ROOT + "/pay";
        public static final String SEND = "/send" + "/" + PathVariable.PAYMENT_GATEWAY_PATH;
        public static final String SUCCESS = "/success";
        public static final String FAILURE = "/failure";
    }
}
