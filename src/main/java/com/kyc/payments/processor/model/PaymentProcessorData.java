package com.kyc.payments.processor.model;

import com.kyc.payments.enums.CustomerPaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PaymentProcessorData {

    private Long folio;
    private String account;
    private CustomerPaymentMethodEnum method;
    private Double amount;
    private String motive;
    private Integer office;
    private Long customer;
    private LocalDateTime date;
}
