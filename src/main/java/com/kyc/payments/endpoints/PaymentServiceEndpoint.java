package com.kyc.payments.endpoints;

import com.kyc.payments.exceptions.KycPaymentsException;
import com.kyc.payments.services.PaymentService;
import com.kyc.payments.ws.coretypes.ErrorData;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsRequest;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsResponse;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentRequest;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusPaymentRequest;
import com.kyc.payments.ws.paymenttypes.GetStatusPaymentResponse;
import com.kyc.payments.ws.paymenttypes.MakePaymentRequest;
import com.kyc.payments.ws.paymenttypes.MakePaymentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import java.lang.invoke.MethodHandles;

import static com.kyc.payments.constants.Constants.NAME_SPACE_AUTH_URI;
import static com.kyc.payments.constants.Constants.NAME_SPACE_PAYMENTS_URI;

@Endpoint
public class PaymentServiceEndpoint {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static final String HEADER_AUTH = "{"+NAME_SPACE_AUTH_URI+"}Authentication";

    @Autowired
    private PaymentService paymentService;

    @PayloadRoot(localPart = "MakePaymentRequest", namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public MakePaymentResponse makePayment(@RequestPayload MakePaymentRequest request,
                                           @SoapHeader(HEADER_AUTH) SoapHeaderElement soapHeader) throws KycPaymentsException {

        LOGGER.info("Consumiendo endpoint de pagos");
        return paymentService.payService(request);
    }

    @PayloadRoot(localPart = "GetStatusPaymentRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetStatusPaymentResponse getStatusPayment(@RequestPayload GetStatusPaymentRequest request, SoapHeader soapHeader){

        LOGGER.info("Consumiendo operacion de status payment");
        return paymentService.getStatusPayment(request);
    }

    @PayloadRoot(localPart = "GetHistoricalPaymentsRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetHistoricalPaymentsResponse getHistoricalPayments(@RequestPayload GetHistoricalPaymentsRequest request, SoapHeader soapHeader){

        LOGGER.info("Consumiendo operacion de obtencion de historico de pagos");
        return paymentService.getHistoricalPayments(request);
    }

    @PayloadRoot(localPart = "GetInfoPaymentRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetInfoPaymentResponse getInfoPayment(@RequestPayload GetInfoPaymentRequest request, SoapHeader soapHeader){

        LOGGER.info("Consumiendo operacion de info de pago");
        return paymentService.getInfoPayment(request);
    }

}
