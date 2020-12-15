package com.kyc.payments.util;

import com.kyc.payments.entity.PaymentStatusEntity;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;

import java.sql.Timestamp;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public static Integer getIdStatusPayment(StatusPaymentEnum status){

        Integer id = null;
        for(Map.Entry<Integer,StatusPaymentEnum> entry: enumStatus.entrySet()){
            if(entry.getValue().equals(status)){
                id = entry.getKey();
                break;
            }
        }
        return id;
    }

    public static Timestamp getTimestamp(Date date){
        if(date == null){
            return Timestamp.from(new Date().toInstant());
        }
        return Timestamp.from(date.toInstant());
    }

}
