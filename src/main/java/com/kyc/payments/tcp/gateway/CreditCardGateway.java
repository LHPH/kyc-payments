package com.kyc.payments.tcp.gateway;

import com.kyc.core.model.MessageData;
import com.kyc.payments.processor.model.PaymentProcessorData;
import org.springframework.integration.annotation.MessagingGateway;

import static com.kyc.payments.constants.AppConstants.KYC_CARD_PAYMENT_REQ_CHANNEL;

@MessagingGateway(defaultRequestChannel = KYC_CARD_PAYMENT_REQ_CHANNEL)
public interface CreditCardGateway {

    MessageData processCardPayment(PaymentProcessorData data);
}
