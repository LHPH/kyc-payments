package com.kyc.payments.helpers;

import com.kyc.payments.entity.PaymentEntity;
import com.kyc.payments.entity.ServiceChargeDetailEntity;
import com.kyc.payments.entity.TransactionStatusEntity;
import com.kyc.payments.entity.TransactionsEntity;
import com.kyc.payments.util.PaymentUtils;
import com.kyc.payments.ws.coretypes.ReceiptData;
import com.kyc.payments.ws.coretypes.StatusCharge;
import com.kyc.payments.ws.coretypes.StatusPayment;
import com.kyc.payments.ws.paymenttypes.GetInfoPaymentResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusChargeResponse;
import com.kyc.payments.ws.paymenttypes.GetStatusPaymentResponse;
import org.springframework.stereotype.Component;

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
        receiptData.setDatePayment(latest.getDateFinish());
        receiptData.setAmount(payment.getAmount());
        receiptData.setFolio(payment.getFolio().intValue());
        response.setReceipt(receiptData);

        return response;

    }

}
