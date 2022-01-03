package com.api.client.interceptor;

import com.api.client.contract.APIClient;
import com.util.csv.CsvUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class APIClientInterceptor implements InvocationHandler {
    private static final String CSV_FILE_NAME = "api_executions.csv";

    private APIClient client;
    public APIClientInterceptor(final APIClient client) {
        this.client = client;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Instant start = Instant.now();
        final Object returnObj = method.invoke(this.client, args);
        final Instant end = Instant.now();
        final Duration duration = Duration.between(start, end);
        CsvUtil.writeRecord(CSV_FILE_NAME, this.client.getClass().getName(), method.getName(),
                String.valueOf(duration.toMillis()));
        return returnObj;
    }
}
