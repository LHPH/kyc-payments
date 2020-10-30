package com.kyc.payments.services;

import com.kyc.payments.repositories.PaymentRepository;
import com.kyc.payments.ws.coretypes.ReceiptData;
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
        response.getReceipt().setFolio(BigInteger.valueOf(1000));

        return response;

    }
}
