package com.kyc.payments.helpers;

import org.springframework.stereotype.Component;

@Component
public class PaymentHelper {

    /*
    public GetStatusPaymentResponse getStatusPayment(KycPayment payment){

        GetStatusPaymentResponse response = new GetStatusPaymentResponse();
        StatusPayment status = new StatusPayment();

        status.setFolio(String.valueOf(payment.getFolio()));
        status.setAmount(payment.getAmount());

        List<KycTransaction> transactions = payment.getTransactions();
        KycTransaction latest = transactions.get(0);
        TransactionStatusEntity latestStatus = Optional.ofNullable(latest.getTransactionStatus())
                .orElse(new TransactionStatusEntity());

        status.setIdTransaction(latest.getId());
        status.setFinishDateTransaction(latest.getDateFinish());
        status.setStartDateTransaction(latest.getDateStart());
        status.setStatusTransaction(latestStatus.getDescription());

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

        List<KycPayment> payments = chargeDetail.getServiceCharge().getPayments();

        for(KycPayment payment : payments){
            status.getPayments().add(getStatusPayment(payment).getStatusPayment());
        }

        response.setStatusCharge(status);
        return response;
    }

    public GetInfoPaymentResponse getInfoPayment(KycPayment payment){

        GetInfoPaymentResponse response =  new GetInfoPaymentResponse();
        //List<TransactionsEntity> transactions = payment.getTransactions();
        //Optional<TransactionsEntity> op = transactions.stream().findFirst();

        ReceiptData receiptData = new ReceiptData();
        receiptData.setStatus(PaymentUtils.getStatusPayment(payment.getPaymentStatus()).value());
        receiptData.setDatePayment(payment.getDatePayment());
        receiptData.setAmount(payment.getAmount());
        receiptData.setMotive(payment.getMotive());
        receiptData.setFolio(payment.getFolio().intValue());
        response.setReceipt(receiptData);

        return response;

    }

    public KycPayment preparePayment(PaymentData paymentData){

        PaymentStatusEntity paymentStatus = new PaymentStatusEntity();
        paymentStatus.setId(PaymentUtils.getIdStatusPayment(StatusPaymentEnum.PAYMENT_ONGOING));

        KycPayment payment = new KycPayment();
        payment.setAmount(paymentData.getAmount());
        payment.setMotive(paymentData.getMotive());
        payment.setDatePayment(new Date());
        payment.setPaymentSource(paymentData.getSource());
        payment.setPaymentStatus(paymentStatus);

        return payment;
    }

    public KycTransaction prepareTransaction(KycPaymentOffice bank){

        TransactionStatusEntity transactionStatus = new TransactionStatusEntity();
        transactionStatus.setId(TransactionStatusEnum.SEND.getIdStatusTransaction());

        KycTransaction transaction = new KycTransaction();
        transaction.setBank(bank);
        transaction.setDateStart(new Timestamp(new Date().getTime()));
        transaction.setSource("KYC");
        transaction.setTransactionStatus(transactionStatus);
        transaction.setDestination(bank.getCveBank());

        return transaction;

    }

    public GetHistoricalPaymentsResponse getHistoricalPayments(List<KycPayment> payments){

        GetHistoricalPaymentsResponse response = new GetHistoricalPaymentsResponse();
        for(KycPayment payment: payments){
            response.getPayments().add(getInfoPayment(payment).getReceipt());
        }
        return response;
    }
     */

}
