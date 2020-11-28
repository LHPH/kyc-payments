package com.kyc.payments.enums;

import lombok.Getter;

@Getter
public enum TransactionStatusEnum {

    SUCCESS(1),
    APPROVED(2),
    PENDING(3),
    SEND(4),
    DECLINED(5),
    FAILED(6);

    private int idStatusTransaction;

    private TransactionStatusEnum(int id){
        this.idStatusTransaction = id;
    }

    public static TransactionStatusEnum getInstance(int id){

        for(TransactionStatusEnum value : TransactionStatusEnum.values()){
            if(value.getIdStatusTransaction() == id){
                return value;
            }
        }
        return null;
    }
}
