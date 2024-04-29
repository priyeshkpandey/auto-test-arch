package com.proto.intercept.browsermobproxyimpl;

import com.proto.intercept.ProtocolProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

public class BrowserMobProxyProtocolProxyImpl implements ProtocolProxy {
    private static final String HAR_FILE_NAME = "protocol_traffic_mobproxy.har";

    private BrowserMobProxyServer browserMobProxyServer;
    private Har har;

    public BrowserMobProxyProtocolProxyImpl() {
        this.browserMobProxyServer = new BrowserMobProxyServer();
        EnumSet<CaptureType> captureNetworkLog = CaptureType.getAllContentCaptureTypes();
        captureNetworkLog.addAll(CaptureType.getHeaderCaptureTypes());
        captureNetworkLog.addAll(CaptureType.getRequestCaptureTypes());
        captureNetworkLog.addAll(CaptureType.getResponseCaptureTypes());
        captureNetworkLog.addAll(CaptureType.getCookieCaptureTypes());
        this.browserMobProxyServer.setHarCaptureTypes(captureNetworkLog);
        this.browserMobProxyServer.newHar();
    }

    @Override
    public void start() {
        this.browserMobProxyServer.start(11000);
    }

    @Override
    public void stop() {
        this.browserMobProxyServer.stop();
    }

    public void saveHAR() throws IOException {
        this.har = this.browserMobProxyServer.getHar();
        this.har.writeTo(new File(HAR_FILE_NAME));
    }
}
