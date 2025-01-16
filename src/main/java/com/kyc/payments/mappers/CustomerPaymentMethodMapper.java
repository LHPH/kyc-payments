package com.kyc.payments.mappers;

import com.kyc.core.util.GeneralUtil;
import com.kyc.payments.entity.KycCustomerPaymentMethod;
import com.kyc.payments.ws.coretypes.CustomerPaymentMethod;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerPaymentMethodMapper {

    @Mappings({
            @Mapping(target = "idPaymentMethod",source = "source.method"),
            @Mapping(target = "account",source = "source.account"),
            @Mapping(target = "directDebit",source = "source.directDebit"),
            @Mapping(target = "authorize",source = "source.authorize"),
            @Mapping(target = "active",source = "source.active"),
            @Mapping(target = "creationDate",expression = "java(new java.util.Date())"),
            @Mapping(target = "idCustomer",source = "id"),
    })
    KycCustomerPaymentMethod toEntity(CustomerPaymentMethod source,Long id);

    @Mappings({
            @Mapping(target = "account",source = "source.account"),
            @Mapping(target = "directDebit",source = "source.directDebit"),
            @Mapping(target = "authorize",source = "source.authorize"),
            @Mapping(target = "active",source = "source.active")
    })
    void toUpdateEntity(@MappingTarget KycCustomerPaymentMethod target, CustomerPaymentMethod source);

    @Mappings({
            @Mapping(target = "method",source = "source.idPaymentMethod"),
            @Mapping(target = "account",source = "source",qualifiedByName = "maskAccount"),
            @Mapping(target = "directDebit",source = "source.directDebit"),
            @Mapping(target = "authorize",source = "source.authorize"),
            @Mapping(target = "active",source = "source.active"),
    })
    CustomerPaymentMethod toModel(KycCustomerPaymentMethod source);


    @Named("maskAccount")
    static String maskAccount(KycCustomerPaymentMethod source){

        if(StringUtils.isNoneEmpty(source.getAccount())){
            return GeneralUtil.maskValue(source.getAccount(),"*",4);
        }
        return "";
    }
}
