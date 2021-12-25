package com.api.client.webclient;

import com.api.client.auth.AuthI;
import com.api.client.auth.BasicAuth;
import com.api.client.contract.APIClient;
import com.api.client.contract.APIRequest;
import com.api.client.contract.APIResponse;
import com.api.client.contract.HttpMethod;
import com.api.client.model.response.GenericAPIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

public class WebClientAPIClient implements APIClient {
    private static final Logger LOG = LoggerFactory.getLogger(WebClientAPIClient.class);

    private WebClient client;
    private WebClient.Builder clientBuilder;
    private WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec;
    private WebClient.RequestBodySpec bodySpec;
    private HttpHeaders defaultHeaders;

    @Override
    public APIResponse execute(APIRequest request) {
        LOG.debug("Executing URL: {}, method: {}, body: {}, headers: {}, params: {}",
                request.getURL(), request.getHttpMethod(), request.getBody(), request.getHeaders(), request.getQueryParams());
        HttpMethod httpMethod = request.getHttpMethod();
        Map<String, String> queryParams = request.getQueryParams();
        Map<String, String> pathParams = request.getPathParams();
        LOG.debug("{} request to {}", httpMethod, request.getURL());
        if (request.getHeaders() != null) LOG.debug("Headers: {}", request.getHeaders());
        if (httpMethod == HttpMethod.GET || httpMethod == HttpMethod.DELETE) {
            if (queryParams != null) LOG.debug("Query parameters: {}", queryParams);
            if (pathParams != null) LOG.debug("Path parameters: {}", pathParams);
        } else {
            try {
                LOG.debug("Request body:\n {}", new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(request.getBody().getObject()));
            } catch (JsonProcessingException e) {
                LOG.debug("Could not log the request body"); // logging to debug instead of error since we do not want the test to fail because of this
                LOG.debug(e.getMessage());
            }
        }
        return prepareAndExecute(request);
    }

    private APIResponse prepareAndExecute(final APIRequest request) {
        init();
        configAuth(request.getAuth());
        /*setUrlEncoding(false);
        addHeaders(request.getHeaders());
        addQueryParams(request.getQueryParams());
        addPathParams(request.getPathParams());
        addBody(request.getBody());*/

        switch (request.getHttpMethod()) {
            case GET:
                break;
            case PUT:
                break;
            case POST:
                break;
            case DELETE:
                break;
            case PATCH:
                break;
        }
        WebClient.ResponseSpec response = client.get().retrieve();
        Mono<String> monoResponse = response.bodyToMono(String.class);
        APIResponse apiResponse = GenericAPIResponse.builder()
                .status("monoResponse.")
                .statusCode(String.valueOf("restAssuredResponse.getStatusCode()"))
                .object("restAssuredResponse.body().prettyPrint()")
                .build();
        LOG.debug("Response body:\n {}", "restAssuredResponse.getBody().prettyPrint()");
        return apiResponse;
    }

    private void init() {
        clientBuilder = WebClient.builder();
        defaultHeaders = new HttpHeaders();
    }

    private void configAuth(final AuthI auth) {
        switch (auth.getType()) {
            case NONE:
                break;
            case BASIC:
                final BasicAuth basicAuth = (BasicAuth) auth;
                defaultHeaders.setBasicAuth(basicAuth.getUsername(), basicAuth.getPassword());
                break;
            case CERT:
                break;
        }
    }
}
