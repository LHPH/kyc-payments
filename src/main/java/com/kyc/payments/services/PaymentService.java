package com.kyc.payments.services;

import com.kyc.payments.entity.PaymentEntity;
import com.kyc.payments.entity.ServiceChargeDetailEntity;
import com.kyc.payments.entity.TransactionsEntity;
import com.kyc.payments.helpers.PaymentHelper;
import com.kyc.payments.repositories.PaymentRepository;
import com.kyc.payments.repositories.ServiceChargeDetailRepository;
import com.kyc.payments.util.PaymentUtils;
import com.kyc.payments.ws.coretypes.ReceiptData;
import com.kyc.payments.ws.coretypes.StatusCharge;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;
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
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PaymentHelper paymentHelper;

    @Autowired
    private ServiceChargeDetailRepository serviceChargeDetailRepository;

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

        Long folio = request.getFolio()!=null ?request.getFolio().longValue():-1L;

        PaymentEntity payment = paymentRepository.findByFolio(folio);

        if(payment==null){

        }

        GetStatusPaymentResponse response = paymentHelper.getStatusPayment(payment);

        return response;

    }

    public GetStatusChargeResponse getStatusCharge(GetStatusChargeRequest request){

        String reference = request.getReference();

        ServiceChargeDetailEntity chargeDetail = serviceChargeDetailRepository.findByReference(reference);
        if(chargeDetail== null){

        }

        GetStatusChargeResponse response = paymentHelper.getStatusCharge(chargeDetail);
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

        Long folio = request.getFolio()!=null ?request.getFolio().longValue():-1L;
        PaymentEntity paymentEntity = paymentRepository.findByFolio(folio);
        GetInfoPaymentResponse response =paymentHelper.getInfoPayment(paymentEntity);
        return response;
    }
}
