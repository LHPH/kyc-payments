package com.kyc.payments.util;

import com.kyc.payments.entity.PaymentStatusEntity;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static Map<Integer,StatusPaymentEnum> enumStatus;

    static {
        enumStatus = new HashMap<>();
        enumStatus.put(1,StatusPaymentEnum.PAYMENT_REJECTED);
        enumStatus.put(2,StatusPaymentEnum.PAYMENT_PAID);
        enumStatus.put(3,StatusPaymentEnum.PAYMENT_ONGOING);
    }

    public static StatusPaymentEnum getStatusPayment(PaymentStatusEntity status){

       return enumStatus.get(status.getId());
    }

}
