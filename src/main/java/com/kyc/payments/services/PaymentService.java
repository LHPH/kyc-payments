package com.kyc.payments.services;

import com.kyc.payments.repositories.PaymentRepository;
import com.kyc.payments.ws.coretypes.ReceiptData;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;
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
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Date;

@Service
public class PaymentService {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PaymentRepository paymentRepository;

    public MakePaymentResponse payService(MakePaymentRequest req){

        MakePaymentResponse response = new MakePaymentResponse();

        response.setReceipt(new ReceiptData());
        response.getReceipt().setAmount(req.getPayment().getAmount());
        response.getReceipt().setDatePayment(new Date());
        response.getReceipt().setFolio(1000);

        return response;

    }

    public GetStatusPaymentResponse getStatusPayment(GetStatusPaymentRequest request){

        GetStatusPaymentResponse response = new GetStatusPaymentResponse();
        response.setStatusPayment(new StatusPayment());
        response.getStatusPayment().setFolio("100");
        response.getStatusPayment().setIdTransaction(100132);
        response.getStatusPayment().setFinishDateTransaction(new Date());
        response.getStatusPayment().setStartDateTransaction(new Date());
        response.getStatusPayment().setStatus(StatusPaymentEnum.PAYMENT_PAID);

        return response;

    }

    public GetHistoricalPaymentsResponse getHistoricalPayments(GetHistoricalPaymentsRequest request){

        GetHistoricalPaymentsResponse response = new GetHistoricalPaymentsResponse();
        response.setClientNumber(11323);
        response.getPayments().add(new ReceiptData());
        response.getPayments().get(0).setFolio(121);
        response.getPayments().get(0).setAmount("323234");
        response.getPayments().get(0).setDatePayment(new Date());

        return response;
    }

    public GetInfoPaymentResponse getInfoPayment(GetInfoPaymentRequest request){

        GetInfoPaymentResponse response = new GetInfoPaymentResponse();
        response.setReceipt(new ReceiptData());
        response.getReceipt().setDatePayment(new Date());
        response.getReceipt().setAmount("2222");
        response.getReceipt().setFolio(1);
        return response;
    }
}
