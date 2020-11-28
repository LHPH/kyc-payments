package com.kyc.payments.services;

import com.kyc.payments.entity.BankEntity;
import com.kyc.payments.entity.CustomerEntity;
import com.kyc.payments.entity.PaymentEntity;
import com.kyc.payments.entity.PaymentStatusEntity;
import com.kyc.payments.entity.ServiceChargeDetailEntity;
import com.kyc.payments.entity.TransactionStatusEntity;
import com.kyc.payments.entity.TransactionsEntity;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.exceptions.KycPaymentsException;
import com.kyc.payments.helpers.PaymentHelper;
import com.kyc.payments.repositories.BankRepository;
import com.kyc.payments.repositories.PaymentRepository;
import com.kyc.payments.repositories.ServiceChargeDetailRepository;
import com.kyc.payments.util.PaymentUtils;
import com.kyc.payments.ws.coretypes.ErrorData;
import com.kyc.payments.ws.coretypes.PaymentData;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kyc.payments.constants.Constants.ERROR_CODE_04;
import static com.kyc.payments.constants.Constants.ERROR_CODE_05;
import static com.kyc.payments.constants.Constants.ERROR_CODE_06;
import static com.kyc.payments.constants.Constants.ERROR_DESC_04;
import static com.kyc.payments.constants.Constants.ERROR_DESC_05;
import static com.kyc.payments.constants.Constants.ERROR_DESC_06;

@Service
public class PaymentService {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PaymentHelper paymentHelper;

    @Autowired
    private ServiceChargeDetailRepository serviceChargeDetailRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BankRepository bankRepository;


    public MakePaymentResponse payService(MakePaymentRequest req)  throws KycPaymentsException{

        PaymentData paymentData = req.getPayment();
        String reference = paymentData.getReference();
        LOGGER.info("Buscando si existe la referencia del cargo");
        ServiceChargeDetailEntity chargeDetail = serviceChargeDetailRepository.findByReference(reference);
        if(chargeDetail==null){

        }
        LOGGER.info("Verificando si el cargo ya esta pagado");
        Boolean isPaid = chargeDetail.getPaid() != null && chargeDetail.getPaid();
        if(isPaid){

        }
        LOGGER.info("Procesando datos para el pago");
        PaymentStatusEntity paymentStatus = new PaymentStatusEntity();
        paymentStatus.setId(PaymentUtils.getIdStatusPayment(StatusPaymentEnum.PAYMENT_ONGOING));

        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(paymentData.getAmount());
        payment.setMotive(paymentData.getMotive());
        payment.setPaymentSource(paymentData.getSource());
        payment.setServiceCharge(chargeDetail.getServiceCharge());
        payment.setPaymentStatus(paymentStatus);

        LOGGER.info("Verificando si el banco es autorizado");
        BankEntity bank = bankRepository.findByCveBank(paymentData.getIdBank());
        if(bank == null){

        }

        LOGGER.info("Preparando datos para la transaccion");
        TransactionStatusEntity transactionStatus = new TransactionStatusEntity();
        transactionStatus.setId(TransactionStatusEnum.SEND.getIdStatusTransaction());

        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setBank(bank);
        transaction.setDateStart(new Timestamp(new Date().getTime()));
        transaction.setSource("KYC");
        transaction.setTransactionStatus(transactionStatus);
        transaction.setDestination(bank.getCveBank());
        transaction.setPayment(payment);

        if(payment.getTransactions() == null){
            payment.setTransactions(new ArrayList<>());
        }
        payment.getTransactions().add(transaction);

        LOGGER.info("Guardando informacion del pago");
      //  PaymentEntity result = paymentRepository.save(payment);
        //Long folio = result.getFolio();
        Long folio = 100L;
        LOGGER.info("Se ha registrado el pago cuya referencia {} esta asociada al folio {}",reference,folio);

        MakePaymentResponse response = new MakePaymentResponse();
        response.setReceipt(new ReceiptData());
        response.getReceipt().setAmount(req.getPayment().getAmount());
        response.getReceipt().setDatePayment(new Date());
        response.getReceipt().setFolio(folio.intValue());

        return response;

    }

    public GetStatusPaymentResponse getStatusPayment(GetStatusPaymentRequest request) throws KycPaymentsException {

        Long folio = request.getFolio()!=null ?request.getFolio().longValue():-1L;

        PaymentEntity payment = paymentRepository.findByFolio(folio);
        if(payment==null){
            throw new KycPaymentsException(new ErrorData(ERROR_CODE_05,ERROR_DESC_05));
        }

        GetStatusPaymentResponse response = paymentHelper.getStatusPayment(payment);

        return response;

    }

    @Transactional
    public GetStatusChargeResponse getStatusCharge(GetStatusChargeRequest request) throws KycPaymentsException {

        String reference = request.getReference();

        ServiceChargeDetailEntity chargeDetail = serviceChargeDetailRepository.findByReference(reference);
        if(chargeDetail== null){
            throw new KycPaymentsException(new ErrorData(ERROR_CODE_04,ERROR_DESC_04));
        }

        GetStatusChargeResponse response = paymentHelper.getStatusCharge(chargeDetail);
        return response;

    }

    public GetHistoricalPaymentsResponse getHistoricalPayments(GetHistoricalPaymentsRequest request) throws KycPaymentsException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomerEntity customer = (CustomerEntity) auth.getPrincipal();

        GetHistoricalPaymentsResponse response = new GetHistoricalPaymentsResponse();
        response.setClientNumber(11323);
        response.getPayments().add(new ReceiptData());
        response.getPayments().get(0).setFolio(121);
        response.getPayments().get(0).setAmount("323234");
        response.getPayments().get(0).setDatePayment(new Date());

        return response;
    }

    public GetInfoPaymentResponse getInfoPayment(GetInfoPaymentRequest request) throws KycPaymentsException {

        Long folio = request.getFolio()!=null ?request.getFolio().longValue():-1L;
        PaymentEntity paymentEntity = paymentRepository.findByFolio(folio);
        if(paymentEntity==null){
            throw new KycPaymentsException(new ErrorData(ERROR_CODE_06,ERROR_DESC_06));
        }
        GetInfoPaymentResponse response =paymentHelper.getInfoPayment(paymentEntity);
        return response;
    }
}
