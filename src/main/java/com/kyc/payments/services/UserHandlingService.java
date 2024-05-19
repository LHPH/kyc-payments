package com.kyc.payments.services;

import com.kyc.core.enums.KycUserTypeEnum;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.payments.entity.KycCustomer;
import com.kyc.payments.repositories.KycCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.util.Optional;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;

@Service
public class UserHandlingService {

    @Autowired
    private KycCustomerRepository kycCustomerRepository;

    @Autowired
    private KycMessages kycMessages;

    public Long getIdCustomer(SecureKycUser user){

        KycUserTypeEnum type = user.getUserType();
        if(KycUserTypeEnum.CUSTOMER.equals(type)){

            Optional<KycCustomer> opCustomer = kycCustomerRepository.findByIdUser(user.getId());
            if(opCustomer.isPresent()){
                return opCustomer.get().getId();
            }
        }
        MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
        throw KycSoapException.builderSoapException()
                .faultCode(SoapFaultDefinition.CLIENT)
                .errorData(messageData)
                .inputData(user.getId())
                .build();
    }
}
