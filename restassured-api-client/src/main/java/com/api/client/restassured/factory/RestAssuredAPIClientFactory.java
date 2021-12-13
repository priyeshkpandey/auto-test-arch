package com.api.client.restassured.factory;

import com.api.client.contract.APIClient;
import com.api.client.factory.APIClientFactoryI;
import com.api.client.restassured.RestAssuredClient;

public class RestAssuredAPIClientFactory implements APIClientFactoryI {
    @Override
    public APIClient get() {
        return new RestAssuredClient();
    }
}
