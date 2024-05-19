package com.kyc.payments.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponsePaymentProcessorEnum {

    SUCCESS(0),
    REJECT(1),
    ERROR(-1),
    UNKNOWN(-2);

    private final int code;

    public static ResponsePaymentProcessorEnum getInstance(String code){

        ResponsePaymentProcessorEnum result = UNKNOWN;

        for(ResponsePaymentProcessorEnum value : ResponsePaymentProcessorEnum.values()){

            if(String.valueOf(value.getCode()).equals(code)){
                result = value;
                break;
            }
        }
        return result;
    }
}
