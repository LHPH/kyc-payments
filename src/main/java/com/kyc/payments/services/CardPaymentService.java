package com.kyc.payments.services;

import com.kyc.core.enums.KycUserTypeEnum;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.persistence.entity.KycParameter;
import com.kyc.core.persistence.repositories.KycParameterRepository;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.core.util.DateUtil;
import com.kyc.payments.entity.KycCustomerBillControl;
import com.kyc.payments.entity.KycCustomerPaymentMethod;
import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.enums.CustomerPaymentMethodEnum;
import com.kyc.payments.enums.PaymentStatusEnum;
import com.kyc.payments.enums.ResponsePaymentProcessorEnum;
import com.kyc.payments.enums.TransactionStatusEnum;
import com.kyc.payments.mappers.PaymentMapper;
import com.kyc.payments.processor.model.PaymentProcessorData;
import com.kyc.payments.repositories.KycCustomerPaymentMethodRepository;
import com.kyc.payments.ws.coretypes.PaymentData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.util.Optional;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_004;

@Service
public class CardPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private RecordPaymentService recordPaymentService;
    @Autowired
    private KycCustomerPaymentMethodRepository kycCustomerPaymentMethodRepository;
    @Autowired
    private KycParameterRepository kycParameterRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private UserHandlingService userHandlingService;
    @Autowired
    private KycMessages kycMessages;

    public KycPayment executeCardPayment(PaymentData paymentData){

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();

        if(KycUserTypeEnum.CUSTOMER.equals(user.getUserType())){

            Optional<KycCustomerPaymentMethod> opMethod = kycCustomerPaymentMethodRepository.findByIdCustomerAndIdPaymentMethod(id,paymentData.getMethod());
            if(opMethod.isPresent()){

                KycCustomerBillControl billControl = billService.getCustomerBill(paymentData);
                Long idCustomer = userHandlingService.getIdCustomer(user);
                try {
                    String account = opMethod.get().getAccount();

                    KycPayment kycPayment = paymentMapper.cardPayment(paymentData, id, account,idCustomer);
                    KycPayment kycPaymentResult = recordPaymentService.savePayment(kycPayment, TransactionStatusEnum.SEND);

                    ResponsePaymentProcessorEnum resultProcessor = processPayment(kycPaymentResult);
                    switch (resultProcessor) {
                        case SUCCESS:
                            recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.APPROVED);
                            kycPaymentResult.setIdStatus(PaymentStatusEnum.PAID.getId());
                            billService.payBill(billControl);
                            recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.SUCCESS);
                            break;
                        case REJECT:
                            recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.DECLINED);
                            kycPaymentResult.setIdStatus(PaymentStatusEnum.REJECTED.getId());
                            recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.SUCCESS);
                        default:
                            recordPaymentService.savePayment(kycPaymentResult, TransactionStatusEnum.FAILED);
                    }
                    return kycPaymentResult;
                } catch (Exception ex) {
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

    private ResponsePaymentProcessorEnum processPayment(KycPayment kycPayment){

        KycParameter kycParameter = kycParameterRepository.getKey("KYC_PAYMENT_CARD_AUTH")
                .orElseThrow();

        PaymentProcessorData paymentProcessorData = PaymentProcessorData.builder()
                .operation("001P")
                .source("KYC")
                .auth(kycParameter.getValue())
                .value(layoutCard(kycPayment))
                .build();

        return ResponsePaymentProcessorEnum.SUCCESS;
    }

    //CC|12345|00000123456789|2000.00|PAY SERVICE|1|1000|20241223160819
    private String layoutCard(KycPayment kycPayment){

        CustomerPaymentMethodEnum method = CustomerPaymentMethodEnum.getInstance(kycPayment.getPaymentMethod());

        StringBuilder sb = new StringBuilder();
        sb.append(method == CustomerPaymentMethodEnum.CREDIT_CARD ? "CC": "DC");
        sb.append("|");
        sb.append(kycPayment.getFolio());
        sb.append("|");
        sb.append(StringUtils.leftPad(kycPayment.getAccount(),15,"0"));
        sb.append("|");
        sb.append(kycPayment.getAmount());
        sb.append("|");
        sb.append(kycPayment.getMotive());
        sb.append("|");
        sb.append(kycPayment.getIdPaymentOffice());
        sb.append("|");
        sb.append(kycPayment.getIdUser());
        sb.append("|");
        sb.append(DateUtil.dateLocalTimeToString(kycPayment.getDatePayment(),"yyyyMMddHHmmss"));

        return sb.toString();
    }
}
