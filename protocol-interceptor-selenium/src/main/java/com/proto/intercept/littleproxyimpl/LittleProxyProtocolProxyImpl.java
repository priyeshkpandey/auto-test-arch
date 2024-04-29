package com.proto.intercept.littleproxyimpl;

import com.proto.intercept.ProtocolProxy;
import com.util.csv.CsvUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import java.io.IOException;

public class LittleProxyProtocolProxyImpl extends HttpFiltersSourceAdapter implements ProtocolProxy {
    private static final String CSV_FILE_NAME = "protocol_traffic.csv";

    private HttpProxyServer server;

    @Override
    public void start() {
        this.server = DefaultHttpProxyServer.bootstrap()
                .withPort(11000).withFiltersSource(this).start();

    }

    @Override
    public void stop() {
        this.server.stop();
    }

    @Override
    public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        return new HttpFiltersAdapter(originalRequest) {
            @Override
            public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                try {
                    CsvUtil.writeRecord(CSV_FILE_NAME, httpObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public HttpObject serverToProxyResponse(HttpObject httpObject) {
                try {
                    CsvUtil.writeRecord(CSV_FILE_NAME, httpObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return httpObject;
            }
        };
    }
}
