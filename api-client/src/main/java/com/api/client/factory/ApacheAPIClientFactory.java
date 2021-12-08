package com.api.client.factory;

import com.api.client.apache.ApacheClient;
import com.api.client.contract.APIClient;

public class ApacheAPIClientFactory implements APIClientFactoryI {
    @Override
    public APIClient get() {
        return new ApacheClient();
    }
}
