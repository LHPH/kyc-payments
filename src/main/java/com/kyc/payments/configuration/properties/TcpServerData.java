package com.kyc.payments.configuration.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TcpServerData {
    private String host;
    private int port;
    private int timeout;
}
