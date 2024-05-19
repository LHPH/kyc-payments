package com.kyc.payments.services;

import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.entity.KycTransaction;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.repositories.KycPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Service
public class RecordPaymentService {

    @Autowired
    private KycPaymentRepository kycPaymentRepository;

    @Transactional
    public KycPayment savePayment(KycPayment kycPayment, TransactionStatusEnum transactionStatus){

        if(kycPayment.getTransactions()==null){
            kycPayment.setTransactions(new ArrayList<>());
        }

        KycTransaction kycTransaction = new KycTransaction();
        kycTransaction.setPayment(kycPayment);
        kycTransaction.setDateTransaction(new Date());
        kycTransaction.setIdStatus(transactionStatus.getIdStatusTransaction());
        kycPayment.getTransactions().add(kycTransaction);

        return kycPaymentRepository.save(kycPayment);
    }
}
