package com.api.client.factory;

public class APIClientFactoryBuilder {
    private static RestAssuredAPIClientFactory restAssuredAPIClientFactory;

    public static APIClientFactoryI getRestAssuredAPIClientFactory() {
        if (null == restAssuredAPIClientFactory) {
            restAssuredAPIClientFactory = new RestAssuredAPIClientFactory();
        }
        return restAssuredAPIClientFactory;
    }
}
