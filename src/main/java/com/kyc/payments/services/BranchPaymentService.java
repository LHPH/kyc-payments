package com.kyc.payments.services;

import com.kyc.core.enums.KycUserTypeEnum;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.payments.entity.KycCustomerBillControl;
import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.enums.PaymentStatusEnum;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.mappers.PaymentMapper;
import com.kyc.payments.ws.coretypes.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_004;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_005;

@Service
public class BranchPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private RecordPaymentService recordPaymentService;
    @Autowired
    private BillService billService;
    @Autowired
    private KycMessages kycMessages;

    public KycPayment executeBranchPayment(PaymentData paymentData){

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();

        if(KycUserTypeEnum.EXECUTIVE.equals(user.getUserType())){

            KycCustomerBillControl billControl = billService.getCustomerBill(paymentData);
           try{

               KycPayment kycPayment = paymentMapper.branchPayment(paymentData,id,billControl.getIdCustomer());
               KycPayment kycPaymentResult =  recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.APPROVED);
               billService.payBill(billControl);

               kycPaymentResult.setIdStatus(PaymentStatusEnum.PAID.getId());
               return recordPaymentService.savePayment(kycPaymentResult,TransactionStatusEnum.SUCCESS);
           }
           catch(Exception ex){
               MessageData messageData = kycMessages.getMessage(ERROR_CODE_004);
               throw KycSoapException.builderSoapException()
                       .faultCode(SoapFaultDefinition.SERVER)
                       .errorData(messageData)
                       .inputData(id)
                       .exception(ex)
                       .build();
           }
        }
        MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
        throw KycSoapException.builderSoapException()
                .faultCode(SoapFaultDefinition.CLIENT)
                .errorData(messageData)
                .inputData(id)
                .build();
    }
}
