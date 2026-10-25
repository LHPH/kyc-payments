package com.kyc.payments.services;

import com.kyc.core.enums.KycUserTypeEnum;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.payments.entity.KycCustomerBillControl;
import com.kyc.payments.entity.KycCustomerPaymentMethod;
import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.enums.CardPaymentProcessorResponseCodeEnum;
import com.kyc.payments.enums.CustomerPaymentMethodEnum;
import com.kyc.payments.enums.PaymentStatusEnum;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.mappers.PaymentMapper;
import com.kyc.payments.processor.model.PaymentProcessorData;
import com.kyc.payments.repositories.KycCustomerPaymentMethodRepository;
import com.kyc.payments.tcp.gateway.CreditCardGateway;
import com.kyc.payments.ws.coretypes.PaymentData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.util.Optional;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_004;

@Slf4j
@Service
public class CardPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private RecordPaymentService recordPaymentService;
    @Autowired
    private KycCustomerPaymentMethodRepository kycCustomerPaymentMethodRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private UserHandlingService userHandlingService;
    @Autowired
    private CreditCardGateway creditCardGateway;
    @Autowired
    private KycMessages kycMessages;

    public KycPayment executeCardPayment(PaymentData paymentData){

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();

        if(KycUserTypeEnum.CUSTOMER.equals(user.getUserType())){

            Long idCustomer = userHandlingService.getIdCustomer(user);

            log.info("Retrieving customer payment methods from {}", idCustomer);
            Optional<KycCustomerPaymentMethod> opMethod = kycCustomerPaymentMethodRepository.findByIdCustomerAndIdPaymentMethod(idCustomer,paymentData.getMethod());
            if(opMethod.isPresent()){

                log.info("Retrieve bill data from {}",idCustomer);
                KycCustomerBillControl billControl = billService.getCustomerBill(paymentData);
                try {

                    String account = opMethod.get().getAccount();

                    log.info("Creating payment data");
                    KycPayment kycPayment = paymentMapper.cardPayment(paymentData, id, account,idCustomer);
                    log.info("Applying SEND transaction");
                    KycPayment kycPaymentResult = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.SEND);

                    CardPaymentProcessorResponseCodeEnum resultProcessor = processPayment(kycPaymentResult);
                    log.info("The result of card payment is {}", resultProcessor);
                    kycPaymentResult = switch (resultProcessor.getResult()) {
                        case SUCCESS -> processSuccessfulPayment(kycPaymentResult, billControl);
                        case REJECT -> processRejectedPayment(kycPaymentResult);
                        default -> recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.FAILED);
                    };
                    return kycPaymentResult;
                } catch (Exception ex) {

                    log.error("Unexpected error to process the payment ",ex);
                    MessageData messageData = kycMessages.getMessage(ERROR_CODE_004);
                    throw KycSoapException.builderSoapException()
                            .faultCode(SoapFaultDefinition.SERVER)
                            .errorData(messageData)
                            .exception(ex)
                            .inputData(id)
                            .build();
                }
            }
        }
        MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
        throw KycSoapException.builderSoapException()
                .faultCode(SoapFaultDefinition.CLIENT)
                .errorData(messageData)
                .inputData(id)
                .build();
    }

    private CardPaymentProcessorResponseCodeEnum processPayment(KycPayment kycPayment){

        PaymentProcessorData paymentProcessorData = PaymentProcessorData.builder()
                .folio(kycPayment.getFolio())
                .method(CustomerPaymentMethodEnum.getInstance(kycPayment.getPaymentMethod()))
                .account(kycPayment.getAccount())
                .amount(kycPayment.getAmount())
                .motive(kycPayment.getMotive())
                .customer(kycPayment.getIdCustomer())
                .office(kycPayment.getIdPaymentOffice())
                .date(kycPayment.getDatePayment())
                .build();

        try{
            log.info("Calling the card payment processor");
            MessageData result = creditCardGateway.processCardPayment(paymentProcessorData);
            log.info("The result received by the card payment processor is {}", result);
            return CardPaymentProcessorResponseCodeEnum.getInstance(result.getCode());
        }
        catch (Exception ex){
            log.error("The card payment processor return error ",ex);
            return CardPaymentProcessorResponseCodeEnum.UNAVAILABLE_SERVICE;
        }
    }

    private KycPayment processSuccessfulPayment(KycPayment kycPayment,KycCustomerBillControl billControl){

        log.info("Applying APPROVED transaction");
        kycPayment = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.APPROVED);
        log.info("Change payment status to PAID");
        kycPayment.setIdStatus(PaymentStatusEnum.PAID.getId());
        log.info("Updating bill");
        billService.payBill(billControl);
        log.info("Applying SUCCESS transaction");
        kycPayment = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.SUCCESS);
        return kycPayment;
    }

    private KycPayment processRejectedPayment(KycPayment kycPayment){

        log.info("Applying DECLINED transaction");
        kycPayment = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.DECLINED);
        log.info("Change payment status to REJECTED");
        kycPayment.setIdStatus(PaymentStatusEnum.REJECTED.getId());
        log.info("Applying SUCCESS transaction");
        kycPayment = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.SUCCESS);
        return kycPayment;
    }
}
