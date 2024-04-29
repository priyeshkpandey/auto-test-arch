package com.proto.intercept;

import com.proto.intercept.browsermobproxyimpl.BrowserMobProxyProtocolProxyImpl;
import com.proto.intercept.littleproxyimpl.LittleProxyProtocolProxyImpl;

public class ProtocolProxyFactory {
    private static ProtocolProxy protocolProxy;

    public static ProtocolProxy getLittleProxyProtocolProxy() {
        if (null == protocolProxy) {
            protocolProxy = new LittleProxyProtocolProxyImpl();
        }
        return protocolProxy;
    }

    public static ProtocolProxy getBrowserMobProxyProtocolProxy() {
        if (null == protocolProxy) {
            protocolProxy = new BrowserMobProxyProtocolProxyImpl();
        }
        return protocolProxy;
    }
}
