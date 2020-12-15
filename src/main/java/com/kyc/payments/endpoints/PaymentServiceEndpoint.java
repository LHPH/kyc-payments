package com.kyc.payments.endpoints;

import com.kyc.payments.exceptions.KycPaymentsException;
import com.kyc.payments.services.PaymentService;
import com.kyc.payments.util.SoapHeaderUtil;
import com.kyc.payments.ws.headertypes.DeviceData;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsRequest;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsResponse;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentRequest;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusChargeRequest;
import com.kyc.payments.ws.paymenttypes.GetStatusChargeResponse;
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

import static com.kyc.payments.constants.Constants.NAME_SPACE_HEADER_URI;
import static com.kyc.payments.constants.Constants.NAME_SPACE_PAYMENTS_URI;

@Endpoint
public class PaymentServiceEndpoint {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static final String HEADER_DEVICE_TYPES = "{"+NAME_SPACE_HEADER_URI+"}DeviceData";

    @Autowired
    private PaymentService paymentService;

    @PayloadRoot(localPart = "MakePaymentRequest", namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public MakePaymentResponse makePayment(@RequestPayload MakePaymentRequest request,
                                           @SoapHeader(HEADER_DEVICE_TYPES) SoapHeaderElement soapHeader) throws KycPaymentsException {

        LOGGER.info("Consumiendo endpoint de pagos");
        DeviceData deviceData = SoapHeaderUtil.getHeaderDeviceData(soapHeader);
        LOGGER.info("Request viene de un dispositivo {}",deviceData.getDevice());
        return paymentService.payService(request);
    }

    @PayloadRoot(localPart = "GetStatusPaymentRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetStatusPaymentResponse getStatusPayment(@RequestPayload GetStatusPaymentRequest request,
                                                     org.springframework.ws.soap.SoapHeader soapHeader)
                                                    throws KycPaymentsException{

        LOGGER.info("Consumiendo operacion de status payment");
        DeviceData deviceData = SoapHeaderUtil.getInfoHeaders(soapHeader);
        LOGGER.info("Info de Headers {}",deviceData.getDevice());
        return paymentService.getStatusPayment(request);
    }

    @PayloadRoot(localPart = "GetStatusChargeRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetStatusChargeResponse getStatusCharge(@RequestPayload GetStatusChargeRequest request,
                                                   @SoapHeader(HEADER_DEVICE_TYPES) SoapHeaderElement soapHeader)
                                                    throws KycPaymentsException{

        LOGGER.info("Consumiendo operacion de status charge");
        DeviceData deviceData = SoapHeaderUtil.getHeaderDeviceData(soapHeader);
        LOGGER.info("Request viene de un dispositivo {}",deviceData.getDevice());
        return paymentService.getStatusCharge(request);
    }

    @PayloadRoot(localPart = "GetHistoricalPaymentsRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetHistoricalPaymentsResponse getHistoricalPayments(@RequestPayload GetHistoricalPaymentsRequest request,
                                                               @SoapHeader(HEADER_DEVICE_TYPES) SoapHeaderElement soapHeader)
                                                                throws KycPaymentsException{

        LOGGER.info("Consumiendo operacion de obtencion de historico de pagos");
        DeviceData deviceData = SoapHeaderUtil.getHeaderDeviceData(soapHeader);
        LOGGER.info("Request viene de un dispositivo {}",deviceData.getDevice());
        return paymentService.getHistoricalPayments(request);
    }

    @PayloadRoot(localPart = "GetInfoPaymentRequest",namespace = NAME_SPACE_PAYMENTS_URI)
    @ResponsePayload
    public GetInfoPaymentResponse getInfoPayment(@RequestPayload GetInfoPaymentRequest request,
                                                 @SoapHeader(HEADER_DEVICE_TYPES) SoapHeaderElement soapHeader)
                                                    throws KycPaymentsException{

        LOGGER.info("Consumiendo operacion de info de pago");
        DeviceData deviceData = SoapHeaderUtil.getHeaderDeviceData(soapHeader);
        LOGGER.info("Request viene de un dispositivo {}",deviceData.getDevice());
        return paymentService.getInfoPayment(request);
    }

}
