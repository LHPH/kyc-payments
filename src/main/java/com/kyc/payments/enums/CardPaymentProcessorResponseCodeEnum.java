package com.kyc.payments.enums;

import com.kyc.core.constants.IntegrationConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CardPaymentProcessorResponseCodeEnum {

    UNKNOWN("UNKNOWN",ResponsePaymentProcessorEnum.UNKNOWN),
    ACK(IntegrationConstants.ACK_SUCCESS_CODE,ResponsePaymentProcessorEnum.SUCCESS),
    UNEXPECTED_ERROR("KYC-CARD-PAYMENT-PROCESSOR-001",ResponsePaymentProcessorEnum.ERROR),
    INVALID_DATA("KYC-CARD-PAYMENT-PROCESSOR-002",ResponsePaymentProcessorEnum.ERROR),
    UNAVAILABLE_SERVICE("KYC-CARD-PAYMENT-PROCESSOR-003",ResponsePaymentProcessorEnum.ERROR),
    PROCESSED_OPERATION("KYC-CARD-PAYMENT-PROCESSOR-004",ResponsePaymentProcessorEnum.ERROR),
    REJECTED("KYC-CARD-PAYMENT-PROCESSOR-005",ResponsePaymentProcessorEnum.REJECT)
    ;
    private final String code;
    private final ResponsePaymentProcessorEnum result;

    public static CardPaymentProcessorResponseCodeEnum getInstance(String code) {

        return Arrays.stream(CardPaymentProcessorResponseCodeEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
