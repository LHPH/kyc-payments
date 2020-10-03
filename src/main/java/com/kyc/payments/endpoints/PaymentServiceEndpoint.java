package com.kyc.payments.endpoints;

import com.kyc.payments.ws.paymenttypes.MakePaymentRequest;
import com.kyc.payments.ws.paymenttypes.MakePaymentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;

import java.lang.invoke.MethodHandles;

import static com.kyc.payments.constants.Constants.NAME_SPACE_PAYMENTS_URI;

@Endpoint
public class PaymentServiceEndpoint {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @PayloadRoot(localPart = "makePaymentRequest", namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public MakePaymentResponse makePayment(@RequestPayload MakePaymentRequest request, SoapHeader soapHeader){
        return null;
    }

}
