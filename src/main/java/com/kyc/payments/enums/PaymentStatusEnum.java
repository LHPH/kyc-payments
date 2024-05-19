package com.kyc.payments.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentStatusEnum {

    REJECTED(1),
    PAID(2),
    ONGOING(3);

    private final Integer id;

    public static PaymentStatusEnum getInstance(Integer id){

        for(PaymentStatusEnum value : PaymentStatusEnum.values()){

            if(value.getId().equals(id)){
                return value;
            }
        }
        throw new IllegalArgumentException("Bad value: "+id);
    }
}
