package com.kyc.payments.tcp.transformers;

import com.kyc.core.util.DateUtil;
import com.kyc.payments.enums.CardTypeEnum;
import com.kyc.payments.processor.model.PaymentProcessorData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentLayoutTransformer implements GenericTransformer<PaymentProcessorData, String> {

    @Override
    public String transform(PaymentProcessorData source) {

        return String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                source.getFolio(),
                StringUtils.leftPad(source.getAccount(),16,"0"),
                CardTypeEnum.getInstance(source.getMethod()).getCode(),
                source.getAmount(),
                source.getMotive(),
                source.getOffice(),
                source.getCustomer(),
                DateUtil.dateLocalTimeToString(source.getDate(),"yyyyMMddHHmmss"));
    }
}
