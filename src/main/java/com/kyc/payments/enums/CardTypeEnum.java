package com.kyc.payments.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CardTypeEnum {

    CREDIT_CARD("CC"),
    DEBIT_CARD("DC");

    private final String code;

    public static CardTypeEnum getInstance(CustomerPaymentMethodEnum methodEnum) {

        if(methodEnum == CustomerPaymentMethodEnum.CREDIT_CARD) {
            return CREDIT_CARD;
        }
        if(methodEnum == CustomerPaymentMethodEnum.DEBIT_CARD) {
            return DEBIT_CARD;
        }
        throw new IllegalArgumentException("Unknown card type: " + methodEnum);
    }
}
