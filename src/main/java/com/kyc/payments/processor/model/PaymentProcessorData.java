package com.kyc.payments.processor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PaymentProcessorData {

    private String operation;
    private String source;
    private String value;
    private String auth;
}
