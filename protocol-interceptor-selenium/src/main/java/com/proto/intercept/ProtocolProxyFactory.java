package com.proto.intercept;

import com.proto.intercept.littleproxyimpl.LittleProxyProtocolProxyImpl;

public class ProtocolProxyFactory {
    private static ProtocolProxy protocolProxy;

    public static ProtocolProxy getProtocolProxy() {
        if (null == protocolProxy) {
            protocolProxy = new LittleProxyProtocolProxyImpl();
        }
        return protocolProxy;
    }
}
