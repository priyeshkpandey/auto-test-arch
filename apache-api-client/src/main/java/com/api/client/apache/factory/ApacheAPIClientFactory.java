package com.api.client.apache.factory;

import com.api.client.apache.ApacheClient;
import com.api.client.contract.APIClient;
import com.api.client.factory.APIClientFactoryI;

public class ApacheAPIClientFactory implements APIClientFactoryI {
    @Override
    public APIClient get() {
        return new ApacheClient();
    }
}
