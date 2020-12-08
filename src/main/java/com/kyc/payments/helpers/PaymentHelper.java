package com.kyc.payments.helpers;

import com.kyc.payments.entity.BankEntity;
import com.kyc.payments.entity.PaymentEntity;
import com.kyc.payments.entity.PaymentStatusEntity;
import com.kyc.payments.entity.ServiceChargeDetailEntity;
import com.kyc.payments.entity.TransactionStatusEntity;
import com.kyc.payments.entity.TransactionsEntity;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.util.PaymentUtils;
import com.kyc.payments.ws.coretypes.PaymentData;
import com.kyc.payments.ws.coretypes.ReceiptData;
import com.kyc.payments.ws.coretypes.StatusCharge;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusChargeResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusPaymentResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PaymentHelper {


    public GetStatusPaymentResponse getStatusPayment(PaymentEntity payment){

        GetStatusPaymentResponse response = new GetStatusPaymentResponse();
        StatusPayment status = new StatusPayment();

        status.setFolio(String.valueOf(payment.getFolio()));

        List<TransactionsEntity> transactions = payment.getTransactions();
        TransactionsEntity latest = transactions.get(0);

        status.setIdTransaction(latest.getId());
        status.setFinishDateTransaction(latest.getDateFinish());
        status.setStartDateTransaction(latest.getDateStart());

        status.setStatus(PaymentUtils.getStatusPayment(payment.getPaymentStatus()));

        response.setStatusPayment(status);
        return response;
    }

    public GetStatusChargeResponse getStatusCharge(ServiceChargeDetailEntity chargeDetail){

        GetStatusChargeResponse response = new GetStatusChargeResponse();
        StatusCharge status = new StatusCharge();

        status.setReference(chargeDetail.getReference());
        status.setAmount(chargeDetail.getAmountCharge());
        status.setDateCharge(chargeDetail.getDate());
        status.setStatus(chargeDetail.getPaid()?"PAID":"UNPAID");

        List<PaymentEntity> payments = chargeDetail.getServiceCharge().getPayments();

        for(PaymentEntity payment : payments){
            status.getPayments().add(getStatusPayment(payment).getStatusPayment());
        }

        response.setStatusCharge(status);
        return response;
    }

    public GetInfoPaymentResponse getInfoPayment(PaymentEntity payment){

        GetInfoPaymentResponse response =  new GetInfoPaymentResponse();
        List<TransactionsEntity> transactions = payment.getTransactions();
        Optional<TransactionsEntity> op = transactions.stream().findFirst();
        TransactionsEntity latest = op.orElse(new TransactionsEntity());
        TransactionStatusEntity latestStatus = Optional.ofNullable(latest.getTransactionStatus())
                .orElse(new TransactionStatusEntity());

        ReceiptData receiptData = new ReceiptData();
        receiptData.setStatus(latestStatus.getDescription());
        receiptData.setDatePayment(latest.getDateStart());
        receiptData.setAmount(payment.getAmount());
        receiptData.setFolio(payment.getFolio().intValue());
        response.setReceipt(receiptData);

        return response;

    }

    public PaymentEntity preparePayment(PaymentData paymentData){

        PaymentStatusEntity paymentStatus = new PaymentStatusEntity();
        paymentStatus.setId(PaymentUtils.getIdStatusPayment(StatusPaymentEnum.PAYMENT_ONGOING));

        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(paymentData.getAmount());
        payment.setMotive(paymentData.getMotive());
        payment.setPaymentSource(paymentData.getSource());
        payment.setPaymentStatus(paymentStatus);

        return payment;
    }

    public TransactionsEntity prepareTransaction(BankEntity bank){

        TransactionStatusEntity transactionStatus = new TransactionStatusEntity();
        transactionStatus.setId(TransactionStatusEnum.SEND.getIdStatusTransaction());

        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setBank(bank);
        transaction.setDateStart(new Timestamp(new Date().getTime()));
        transaction.setSource("KYC");
        transaction.setTransactionStatus(transactionStatus);
        transaction.setDestination(bank.getCveBank());

        return transaction;

    }



}
