package com.kyc.payments.services;

import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.payments.entity.KycCustomerBillControl;
import com.kyc.payments.enums.BillStatusEnum;
import com.kyc.payments.repositories.KycCustomerBillControlRepository;
import com.kyc.payments.ws.coretypes.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_007;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_008;

@Service
public class BillService {

    @Autowired
    private KycCustomerBillControlRepository kycCustomerBillControlRepository;

    @Autowired
    private KycMessages kycMessages;

    public KycCustomerBillControl getCustomerBill(PaymentData paymentData){

        Optional<KycCustomerBillControl> opBill = kycCustomerBillControlRepository.findById(paymentData.getBill().longValue());
        if(opBill.isPresent()){

            KycCustomerBillControl bill = opBill.get();
            if(Boolean.TRUE.equals(bill.getSettled())){

                MessageData messageData = kycMessages.getMessage(ERROR_CODE_007);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .inputData(paymentData)
                        .build();
            }

            Set<BillStatusEnum> acceptedStatus = Set.of(
                    BillStatusEnum.VALID,
                    BillStatusEnum.EXPIRED
            );

            if(!acceptedStatus.contains(BillStatusEnum.getInstance(bill.getIdStatus()))) {

                MessageData messageData = kycMessages.getMessage(ERROR_CODE_008);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .inputData(paymentData)
                        .build();
            }

            return bill;
        }
        MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
        throw KycSoapException.builderSoapException()
                .faultCode(SoapFaultDefinition.CLIENT)
                .errorData(messageData)
                .inputData(paymentData)
                .build();
    }

    @Transactional
    public void payBill(KycCustomerBillControl billControl){

        billControl.setSettled(true);
        billControl.setSettlementDate(LocalDateTime.now());
        billControl.setIdStatus(BillStatusEnum.PAID.getId());

        kycCustomerBillControlRepository.save(billControl);
    }
}
