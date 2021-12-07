package com.mock.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class InjectedProps {

    private String serverPort;
    private String serverHostUrl;

    public InjectedProps(final @Value("${server.host.url}") String serverHostUrl, final @Value("${server.port}") String serverPort) {
        this.serverHostUrl = serverHostUrl;
        this.serverPort = serverPort;
    }
}
