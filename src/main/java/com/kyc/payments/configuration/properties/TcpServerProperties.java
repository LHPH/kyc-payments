package com.kyc.payments.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "kyc-config.tcp")
public class TcpServerProperties {

    private String host;
    private int port;
    private int timeout;
}
