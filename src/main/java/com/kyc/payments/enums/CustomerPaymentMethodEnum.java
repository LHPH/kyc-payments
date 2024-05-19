package com.kyc.payments.enums;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CustomerPaymentMethodEnum {

    BRANCH(1),
    DEBIT_CARD(2),
    CREDIT_CARD(3),
    CONVENIENCES_STORES(4);

    private final int id;

    public static CustomerPaymentMethodEnum getInstance(int value){

        for(CustomerPaymentMethodEnum method : CustomerPaymentMethodEnum.values()){
            if(method.getId() == value){
                return method;
            }
        }
        throw new IllegalArgumentException("Bad value "+value);
    }
}
