package com.kyc.payments.endpoints;

import com.kyc.payments.services.CustomerPaymentMethodService;
import com.kyc.payments.services.PaymentInfoService;
import com.kyc.payments.services.PaymentService;
import com.kyc.payments.ws.paymenttypes.GetCustomerPaymentMethodResponse;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsRequest;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsResponse;
import com.kyc.payments.ws.paymenttypes.GetPaymentRequest;
import com.kyc.payments.ws.paymenttypes.GetPaymentResponse;
import com.kyc.payments.ws.paymenttypes.MakePaymentRequest;
import com.kyc.payments.ws.paymenttypes.MakePaymentResponse;
import com.kyc.payments.ws.paymenttypes.UpdateCustomerPaymentMethodRequest;
import com.kyc.payments.ws.paymenttypes.UpdateCustomerPaymentMethodResponse;
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

import static com.kyc.payments.constants.AppConstants.NAME_SPACE_HEADER_URI;
import static com.kyc.payments.constants.AppConstants.NAME_SPACE_PAYMENTS_URI;

@Endpoint
public class PaymentServiceEndpoint {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static final String HEADER_TYPES = "{"+NAME_SPACE_HEADER_URI+"}HeaderData";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerPaymentMethodService customerPaymentMethodService;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @PayloadRoot(localPart = "MakePaymentRequest", namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public MakePaymentResponse makePayment(@RequestPayload MakePaymentRequest request,
                                           @SoapHeader(HEADER_TYPES) SoapHeaderElement soapHeader) {

        LOGGER.info("Consumiendo endpoint de pagos");
        return paymentService.payService(request);
    }


    @PayloadRoot(localPart = "GetPaymentRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetPaymentResponse getPayment(@RequestPayload GetPaymentRequest request,
                                         org.springframework.ws.soap.SoapHeader soapHeader){

        LOGGER.info("Consumiendo operacion de status payment");
        return paymentInfoService.getPayment(request);
    }

    @PayloadRoot(localPart = "GetCustomerPaymentMethodRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetCustomerPaymentMethodResponse getCustomerPaymentMethod(@SoapHeader(HEADER_TYPES) SoapHeaderElement soapHeader) {

       return customerPaymentMethodService.getCustomerPaymentMethods();
    }

    @PayloadRoot(localPart = "GetHistoricalPaymentsRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetHistoricalPaymentsResponse getHistoricalPayments(@RequestPayload GetHistoricalPaymentsRequest request,
                                                               @SoapHeader(HEADER_TYPES) SoapHeaderElement soapHeader) {

        LOGGER.info("Consumiendo operacion de obtencion de historico de pagos");
        return paymentInfoService.getHistoricalPayments(request);
    }

    @PayloadRoot(localPart = "UpdateCustomerPaymentMethodRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public UpdateCustomerPaymentMethodResponse updateCustomerPaymentMethod(@RequestPayload UpdateCustomerPaymentMethodRequest request,
                                                                           @SoapHeader(HEADER_TYPES) SoapHeaderElement soapHeader) {

        LOGGER.info("Consumiendo operacion de info de pago");
        return customerPaymentMethodService.saveCustomerPaymentMethod(request);
    }

}
