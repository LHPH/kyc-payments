package com.kyc.payments.mappers;

import com.kyc.payments.entity.KycPayment;
import com.kyc.payments.enums.PaymentStatusEnum;
import com.kyc.payments.services.CatalogService;
import com.kyc.payments.ws.coretypes.PaymentData;
import com.kyc.payments.ws.coretypes.PaymentRecord;
import com.kyc.payments.ws.coretypes.StatusPaymentEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mappings({
            @Mapping(target = "channel",source = "source.channel"),
            @Mapping(target = "paymentMethod",expression = "java(com.kyc.payments.enums.CustomerPaymentMethodEnum.BRANCH.getId())"),
            @Mapping(target = "amount",source = "source.amount"),
            @Mapping(target = "motive",constant = "PAYMENT"),
            @Mapping(target = "idStatus",expression = "java(com.kyc.payments.enums.PaymentStatusEnum.PAID.getId())"),
            @Mapping(target = "idBill",source = "source.bill"),
            @Mapping(target = "idPaymentOffice",source = "source.office"),
            @Mapping(target = "idUser",source = "id"),
            @Mapping(target = "datePayment",expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "idCustomer",source = "idCustomer"),
    })
    KycPayment branchPayment(PaymentData source,Long id,Long idCustomer);

    @Mappings({
            @Mapping(target = "channel",source = "source.channel"),
            @Mapping(target = "paymentMethod",source = "source.method"),
            @Mapping(target = "amount",source = "source.amount"),
            @Mapping(target = "motive",source = "source.motive"),
            @Mapping(target = "account",source = "account"),
            @Mapping(target = "idStatus",expression = "java(com.kyc.payments.enums.PaymentStatusEnum.ONGOING.getId())"),
            @Mapping(target = "idBill",source = "source.bill"),
            @Mapping(target = "idPaymentOffice",source = "source.office"),
            @Mapping(target = "idUser",source = "id"),
            @Mapping(target = "datePayment",expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "idCustomer",source = "idCustomer"),
    })
    KycPayment cardPayment(PaymentData source,Long id, String account,Long idCustomer);

    @Mappings({
            @Mapping(target = "bill",source = "source.idBill"),
            @Mapping(target = "folio",source = "source.folio"),
            @Mapping(target = "amount",source = "source.amount"),
            @Mapping(target = "reference",source = "source.reference"),
            @Mapping(target = "status",source = "source",qualifiedByName = "getStatusPaymentEnum"),
            @Mapping(target = "date",expression = "java(com.kyc.core.util.DateUtil.localDateTimeToInstant(source.getDatePayment()))"),
    })
    PaymentRecord toModel(KycPayment source, @Context CatalogService catalogService);

    @Named("getStatusPaymentEnum")
    static StatusPaymentEnum getStatusPaymentEnum(KycPayment source){
        PaymentStatusEnum status = PaymentStatusEnum.getInstance(source.getIdStatus());
        switch (status){
            case PAID -> {
                return StatusPaymentEnum.PAYMENT_PAID;
            }
            case ONGOING -> {
                return StatusPaymentEnum.PAYMENT_ONGOING;
            }
            case REJECTED -> {
                return StatusPaymentEnum.PAYMENT_REJECTED;
            }
        }
        return null;
    }

    @AfterMapping
    default void completePaymentRecord(@MappingTarget PaymentRecord target, KycPayment source,
                                       @Context CatalogService catalogService){

        target.setChannel(catalogService.getChannelDesc(source.getChannel()));
        target.setMethod(catalogService.getPaymentMethodsDesc(source.getPaymentMethod()));
        target.setOffice(catalogService.getPaymentOfficeDesc(source.getIdPaymentOffice()));
    }
}
