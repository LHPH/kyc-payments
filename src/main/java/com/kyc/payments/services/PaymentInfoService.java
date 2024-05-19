package com.kyc.payments.services;

import com.kyc.core.enums.KycUserTypeEnum;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.security.SecureKycUser;
import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.helpers.PaymentHelper;
import com.kyc.payments.mappers.PaymentMapper;
import com.kyc.payments.repositories.KycCustomerRepository;
import com.kyc.payments.repositories.KycPaymentRepository;
import com.kyc.payments.ws.coretypes.HistoricalPaymentCriteria;
import com.kyc.payments.ws.coretypes.PaymentRecord;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsRequest;
import com.kyc.payments.ws.paymenttypes.GetHistoricalPaymentsResponse;
import com.kyc.payments.ws.paymenttypes.GetPaymentRequest;
import com.kyc.payments.ws.paymenttypes.GetPaymentResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_002;
import static com.kyc.payments.constants.AppConstants.ERROR_CODE_005;

@Service
public class PaymentInfoService {

    @Autowired
    private PaymentHelper paymentHelper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private KycPaymentRepository paymentRepository;

    @Autowired
    private UserHandlingService userHandlingService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private KycMessages kycMessages;

    public GetPaymentResponse getPayment(GetPaymentRequest req){

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();

        try{
            if(req.getFolio()!=null){
                return getPaymentByFolio(req.getFolio().longValue(),user);
            }
            else if(req.getReference()!=null){
                return getPaymentByReference(req.getReference(),user);
            }
            else{
                return new GetPaymentResponse();
            }
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

    public GetHistoricalPaymentsResponse getHistoricalPayments(GetHistoricalPaymentsRequest req){

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        SecureKycUser user = (SecureKycUser) auth.getDetails();

        Long id = user.getId();
        try{

            GetHistoricalPaymentsResponse result = new GetHistoricalPaymentsResponse();

            HistoricalPaymentCriteria criteria = req.getCriteria();
            LocalDateTime dateStart = criteria.getStart().atStartOfDay();
            LocalDateTime dateFinish = criteria.getFinish().atStartOfDay();
            Integer numRows = ObjectUtils.defaultIfNull(criteria.getLimit(),10);
            Pageable page = PageRequest.of(0, numRows);

            List<PaymentRecord> payments;
            switch (user.getUserType()){
                case CUSTOMER -> {
                    Long idCustomer = userHandlingService.getIdCustomer(user);
                    payments = paymentRepository.findPaymentsFromDateAndCustomer(dateStart,dateFinish,idCustomer,page)
                            .stream()
                            .map(p -> paymentMapper.toModel(p,catalogService))
                            .toList();
                }
                case EXECUTIVE -> {
                    payments = paymentRepository.findPaymentsFromDateAndCustomer(dateStart,dateFinish,req.getCriteria().getCustomer(), page)
                            .stream()
                            .map(p -> paymentMapper.toModel(p,catalogService))
                            .toList();
                }
                default -> {
                    MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
                    throw KycSoapException.builderSoapException()
                            .faultCode(SoapFaultDefinition.CLIENT)
                            .errorData(messageData)
                            .inputData(user.getId())
                            .build();
                }
            }
            result.getPayments().addAll(payments);
            return result;
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

    private GetPaymentResponse getPaymentByFolio(Long folio, SecureKycUser user){

        KycUserTypeEnum type = user.getUserType();

        Optional<KycPayment> opPayment = switch (type) {
            case CUSTOMER -> {
                Long idCustomer = userHandlingService.getIdCustomer(user);
                yield paymentRepository.findByFolioAndCustomer(folio, idCustomer);
            }
            case EXECUTIVE -> paymentRepository.findById(folio);
            default -> {
                MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .inputData(user.getId())
                        .build();
            }
        };

        GetPaymentResponse result = new GetPaymentResponse();
        opPayment.ifPresent(kycPayment -> result.setPayment(paymentMapper.toModel(kycPayment, catalogService)));
        return result;
    }

    private GetPaymentResponse getPaymentByReference(String reference, SecureKycUser user){

        KycUserTypeEnum type = user.getUserType();

        Optional<KycPayment> opPayment = switch (type) {
            case CUSTOMER -> {
                Long idCustomer = userHandlingService.getIdCustomer(user);
                yield paymentRepository.findByReferenceAndCustomer(reference, idCustomer);
            }
            case EXECUTIVE -> paymentRepository.findByReference(reference);
            default -> {
                MessageData messageData = kycMessages.getMessage(ERROR_CODE_002);
                throw KycSoapException.builderSoapException()
                        .faultCode(SoapFaultDefinition.CLIENT)
                        .errorData(messageData)
                        .inputData(user.getId())
                        .build();
            }
        };

        GetPaymentResponse result = new GetPaymentResponse();
        opPayment.ifPresent(kycPayment -> result.setPayment(paymentMapper.toModel(kycPayment, catalogService)));
        return result;
    }
}
