package com.api.client.okhttp;

import com.api.client.auth.AuthI;
import com.api.client.auth.BasicAuth;
import com.api.client.auth.CertAuth;
import com.api.client.contract.*;
import com.api.client.model.response.GenericAPIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.*;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class OkHttpAPIClient implements APIClient {
    private static final Logger LOG = LoggerFactory.getLogger(OkHttpAPIClient.class);

    private Request.Builder requestBuilder;
    private OkHttpClient.Builder clientBuilder;
    private HttpUrl.Builder httpUrlBuilder;
    private HttpUrl httpUrl;
    private RequestBody requestBody;
    private Request request;

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
        httpUrl = HttpUrl.parse(request.getURL());
        httpUrlBuilder = httpUrl.newBuilder();
        init();
        configAuth(request.getAuth());
        addHeaders(request.getHeaders());
        addQueryParams(request.getQueryParams());
        addPathParams(request.getPathParams());
        addBody(request.getBody());
        httpUrl = httpUrlBuilder.build();
        requestBuilder.url(httpUrl);
        switch (request.getHttpMethod()) {
            case GET:
                this.request = requestBuilder.get().build();
                break;
            case PUT:
                this.request = requestBuilder.put(this.requestBody).build();
                break;
            case POST:
                this.request = requestBuilder.post(this.requestBody).build();
                break;
            case DELETE:
                if (null != this.requestBody) {
                    this.request = requestBuilder.delete(this.requestBody).build();
                } else {
                    this.request = requestBuilder.delete().build();
                }
                break;
            case PATCH:
                this.request = requestBuilder.patch(this.requestBody).build();
                break;
        }
        Response response = null;
        try {
            response = clientBuilder.build().newCall(this.request).execute();
        } catch (IOException e) {
            LOG.error("Exception executing the request. ", e);
        } finally {
            if (null != response) {
                response.close();
            }
        }
        APIResponse apiResponse = null;
        if (null != response) {
            apiResponse = GenericAPIResponse.builder()
                    .status(response.message())
                    .statusCode(String.valueOf(response.code()))
                    .object(new BufferedReader(new InputStreamReader(response.body().byteStream()))
                            .lines().parallel().collect(Collectors.joining("\n"))
                    )
                    .build();
        } else {
            apiResponse = GenericAPIResponse.builder().status("NULL").statusCode("NULL").build();
        }
        return apiResponse;
    }

    private void init() {
        requestBuilder  = new Request.Builder();
        clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
    }

    private void configAuth(final AuthI auth) {
        switch (auth.getType()) {
            case NONE:
                break;
            case BASIC:
                final BasicAuth basicAuth = (BasicAuth) auth;
                String credential = Credentials.basic(basicAuth.getUsername(), basicAuth.getPassword());
                requestBuilder.addHeader("Authorization", credential);
                break;
            case CERT:
                try {
                    final CertAuth certAuth = (CertAuth) auth;
                    SSLContext sslContext = SSLContexts.custom()
                            .loadKeyMaterial(readStore(certAuth.getKeyStorePath(), certAuth.getKeyStorePassword()), null)
                            .build();
                    TrustManager TRUST_ALL_CERTS = getTrustManager();
                    clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) TRUST_ALL_CERTS);
                    clientBuilder.hostnameVerifier((hostname, session) -> true);
                } catch (Exception ex) {
                    LOG.error("Exception while initializing cert auth. ", ex);
                }
                break;
        }
    }

    private KeyStore readStore(final String keyStorePath, final String keyStorePass) throws Exception {
        try (InputStream keyStoreStream = this.getClass().getResourceAsStream(keyStorePath)) {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // or "PKCS12"
            keyStore.load(keyStoreStream, keyStorePass.toCharArray());
            return keyStore;
        }
    }

    private TrustManager getTrustManager() {
        TrustManager TRUST_ALL_CERTS = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
        };
        return TRUST_ALL_CERTS;
    }

    private void addHeaders(final Map<String, String> headers) {
        if (null != headers) {
            for (String headerKey : headers.keySet()) {
                requestBuilder.addHeader(headerKey, headers.get(headerKey));
            }
        }
    }

    private void addQueryParams(final Map<String, String> queryParams) {
        if (null != queryParams) {
            for(Map.Entry<String, String> param : queryParams.entrySet()) {
                httpUrlBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }
    }

    private void addPathParams(final Map<String, String> pathParams) {
        if (null != pathParams) {
            List<String> pathSegments = httpUrl.pathSegments();
            if (pathParams.size() == pathSegments.size()) {
                int index = 0;
                for (String pathSegment : pathSegments) {
                    httpUrlBuilder.setPathSegment(index, pathParams.get(pathSegment.substring(1, pathSegment.length() - 1)));
                    index++;
                }
            }
        }
    }

    private void addBody(final APIRequestBody requestBody) {
        if (requestBody.getType().equals(RequestBodyType.MULTIPART)) {
            final MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
            Optional.ofNullable(requestBody.getMultipartBodyList()).ifPresent(multipart -> {
                multipart.forEach(mp -> {
                    if (mp.getContentBody() != null) {
                        multipartBodyBuilder.addFormDataPart(mp.getControlName(), mp.getContentBody());
                    }
                    if (mp.getFile() != null) {
                        multipartBodyBuilder.addFormDataPart(mp.getControlName(), mp.getFile().getAbsolutePath(),
                                RequestBody.create(mp.getFile(), MediaType.parse("text/csv")));
                    }
                });
            });
            this.requestBody = multipartBodyBuilder.build();
        }
        if (null != requestBody.getObject()) {
            try {
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                final String json = new ObjectMapper().writeValueAsString(requestBody.getObject());
                this.requestBody = RequestBody.create(json, JSON);
            } catch (Exception e) {
                LOG.error("Exception creating body. ", e);
            }
        }
    }
}
