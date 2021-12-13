package com.api.client.okhttp.factory;

import com.api.client.contract.APIClient;
import com.api.client.factory.APIClientFactoryI;
import com.api.client.okhttp.OkHttpAPIClient;

public class OkHttpAPIClientFactory implements APIClientFactoryI {
    @Override
    public APIClient get() {
        return new OkHttpAPIClient();
    }
}
