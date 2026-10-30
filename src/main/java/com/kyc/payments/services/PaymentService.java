package com.kyc.payments.services;

import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.core.util.DateUtil;
import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.enums.CustomerPaymentMethodEnum;
import com.kyc.payments.enums.PaymentStatusEnum;
import com.kyc.payments.repositories.KycPaymentOfficeRepository;
import com.kyc.payments.ws.coretypes.PaymentData;
import com.kyc.payments.ws.coretypes.ReceiptData;
import com.kyc.payments.ws.paymenttypes.MakePaymentRequest;
import com.kyc.payments.ws.paymenttypes.MakePaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;


import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_004;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_006;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private KycPaymentOfficeRepository bankRepository;

    @Autowired
    private BranchPaymentService branchPaymentService;

    @Autowired
    private CardPaymentService cardPaymentService;

    @Autowired
    private KycMessages kycMessages;


    public MakePaymentResponse payService(MakePaymentRequest req) {

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();

        PaymentData paymentData = req.getPayment();
        Integer idBill = paymentData.getBill();
        Integer idChannel = paymentData.getChannel();
        Integer idOffice = paymentData.getOffice();

        CustomerPaymentMethodEnum method = CustomerPaymentMethodEnum.getInstance(paymentData.getMethod());
        MakePaymentResponse result = new MakePaymentResponse();
        KycPayment kycPayment = null;
        switch (method){
            case BRANCH:
                log.info("Applying payment in Branch");
                kycPayment = branchPaymentService.executeBranchPayment(paymentData);
                result.setReceipt(generateReceiptData(kycPayment));
                break;
            case CREDIT_CARD:
            case DEBIT_CARD:
                log.info("Applying payment with Card {}",method);
                kycPayment = cardPaymentService.executeCardPayment(paymentData);
                result.setReceipt(generateReceiptData(kycPayment));
                break;
            case CONVENIENCES_STORES:
                MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .inputData(id)
                        .build();
        }
        return result;
    }

    private ReceiptData generateReceiptData(KycPayment kycPayment){

        Integer paymentStatus = kycPayment.getIdStatus();
        MessageData messageData = null;
        switch (PaymentStatusEnum.getInstance(paymentStatus)){
            case PAID:
                log.info("Generating receipt of payment");
                ReceiptData receiptData = new ReceiptData();
                receiptData.setFolio(kycPayment.getFolio().intValue());
                receiptData.setAmount(kycPayment.getAmount());
                receiptData.setDate(DateUtil.localDateTimeToInstant(kycPayment.getDatePayment()));
                return receiptData;
            case REJECTED:
                messageData = kycMessages.getMessage(ERROR_CODE_006);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .build();
            default:
                messageData = kycMessages.getMessage(ERROR_CODE_004);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.SERVER)
                        .errorData(messageData)
                        .build();
        }
    }
}
