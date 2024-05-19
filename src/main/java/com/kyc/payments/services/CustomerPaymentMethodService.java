package com.kyc.payments.services;

import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.payments.entity.KycCustomerPaymentMethod;
import com.kyc.payments.mappers.CustomerPaymentMethodMapper;
import com.kyc.payments.repositories.KycCustomerPaymentMethodRepository;
import com.kyc.payments.ws.paymenttypes.GetCustomerPaymentMethodResponse;
import com.kyc.payments.ws.paymenttypes.UpdateCustomerPaymentMethodRequest;
import com.kyc.payments.ws.paymenttypes.UpdateCustomerPaymentMethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.util.List;
import java.util.Optional;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_005;

@Service
public class CustomerPaymentMethodService {


    @Autowired
    private KycCustomerPaymentMethodRepository repository;

    @Autowired
    private CustomerPaymentMethodMapper mapper;

    @Autowired
    private UserHandlingService userHandlingService;

    @Autowired
    private KycMessages kycMessages;

    public UpdateCustomerPaymentMethodResponse saveCustomerPaymentMethod(UpdateCustomerPaymentMethodRequest req){

       try{

           Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
           SecureKycUser user = (SecureKycUser) auth.getDetails();

           Long id = user.getId();

           Optional<KycCustomerPaymentMethod> opMethod = repository.findByIdCustomerAndIdPaymentMethod(id,req.getPaymentMethod().getMethod());

           KycCustomerPaymentMethod method;
           if(opMethod.isPresent()){

               method = opMethod.get();
               mapper.toUpdateEntity(method,req.getPaymentMethod());
           }
           else{
               Long idCustomer = userHandlingService.getIdCustomer(user);
               method = mapper.toEntity(req.getPaymentMethod(),idCustomer);
           }

           KycCustomerPaymentMethod result = repository.save(method);

           UpdateCustomerPaymentMethodResponse response = new UpdateCustomerPaymentMethodResponse();
           response.setMessage("SUCCESS SAVING CUSTOMER PAYMENT METHOD");

           return response;
       }
       catch(DataAccessException ex){

           MessageData messageData = kycMessages.getMessage(ERROR_CODE_005);
           throw KycSoapException.builderSoapException()
                   .faultCode(SoapFaultDefinition.SERVER)
                   .errorData(messageData)
                   .exception(ex)
                   .build();
       }
    }

    public GetCustomerPaymentMethodResponse getCustomerPaymentMethods(){

       try{

           Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
           SecureKycUser user = (SecureKycUser) auth.getDetails();

           Long id = user.getId();
           Long idCustomer  = userHandlingService.getIdCustomer(user);

           List<KycCustomerPaymentMethod> result = repository.findAllCustomerPaymentMethod(idCustomer);

           GetCustomerPaymentMethodResponse response = new GetCustomerPaymentMethodResponse();

           response.getPaymentMethods()
                   .addAll(result.stream()
                           .map(mapper::toModel)
                           .toList());
           return response;
       }
       catch(DataAccessException ex){

           MessageData messageData = kycMessages.getMessage(ERROR_CODE_005);
           throw KycSoapException.builderSoapException()
                   .faultCode(SoapFaultDefinition.SERVER)
                   .errorData(messageData)
                   .exception(ex)
                   .build();
       }
    }

}
