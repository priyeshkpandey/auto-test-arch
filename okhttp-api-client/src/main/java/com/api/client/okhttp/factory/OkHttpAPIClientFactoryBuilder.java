package com.api.client.okhttp.factory;

public class OkHttpAPIClientFactoryBuilder {
    private static OkHttpAPIClientFactory okHttpAPIClientFactory;

    public static OkHttpAPIClientFactory getOkHttpAPIClientFactory() {
        if (null == okHttpAPIClientFactory) {
            okHttpAPIClientFactory = new OkHttpAPIClientFactory();
        }
        return okHttpAPIClientFactory;
    }
}
