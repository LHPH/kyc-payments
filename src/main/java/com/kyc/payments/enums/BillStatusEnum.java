package com.kyc.payments.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BillStatusEnum {

    PAID(1),
    CANCELED(2),
    EXPIRED(3),
    VALID(4);

    private final Integer id;

    public static BillStatusEnum getInstance(int id){
        return Arrays.stream(BillStatusEnum.values())
                .filter(value -> value.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
