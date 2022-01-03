package com.api.client.okhttp.factory;

import com.api.client.contract.APIClient;
import com.api.client.factory.APIClientFactoryI;
import com.api.client.interceptor.APIClientInterceptor;
import com.api.client.okhttp.OkHttpAPIClient;

import java.lang.reflect.Proxy;

public class OkHttpAPIClientFactory implements APIClientFactoryI {
    @Override
    public APIClient get() {
        return (APIClient) Proxy.newProxyInstance(OkHttpAPIClient.class.getClassLoader(), new Class[]{APIClient.class},
                new APIClientInterceptor(new OkHttpAPIClient()));
    }
}
