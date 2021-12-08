package com.api.client.factory;

public class APIClientFactoryBuilder {
    private static RestAssuredAPIClientFactory restAssuredAPIClientFactory;
    private static ApacheAPIClientFactory apacheAPIClientFactory;

    public static APIClientFactoryI getRestAssuredAPIClientFactory() {
        if (null == restAssuredAPIClientFactory) {
            restAssuredAPIClientFactory = new RestAssuredAPIClientFactory();
        }
        return restAssuredAPIClientFactory;
    }

    public static APIClientFactoryI getApacheAPIClientFactory() {
        if (null == apacheAPIClientFactory) {
            apacheAPIClientFactory = new ApacheAPIClientFactory();
        }
        return apacheAPIClientFactory;
    }
}
