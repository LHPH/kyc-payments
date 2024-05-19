package com.kyc.payments.services;

import com.kyc.payments.entity.KycChannel;
import com.kyc.payments.entity.KycPaymentMethod;
import com.kyc.payments.entity.KycPaymentOffice;
import com.kyc.payments.repositories.KycChannelRepository;
import com.kyc.payments.repositories.KycPaymentMethodsRepository;
import com.kyc.payments.repositories.KycPaymentOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private KycPaymentOfficeRepository kycPaymentOfficeRepository;

    @Autowired
    private KycChannelRepository kycChannelRepository;

    @Autowired
    private KycPaymentMethodsRepository kycPaymentMethodsRepository;

    @Cacheable(value = "CATALOG")
    public String getChannelDesc(Integer id){
        return kycChannelRepository.findById(id)
                .map(KycChannel::getName)
                .orElse("");
    }

    @Cacheable(value = "CATALOG")
    public String getPaymentMethodsDesc(Integer id){
        return kycPaymentMethodsRepository.findById(id)
                .map(KycPaymentMethod::getName)
                .orElse("");
    }

    @Cacheable(value = "CATALOG")
    public String getPaymentOfficeDesc(Integer id){
        return kycPaymentOfficeRepository.findById(id)
                .map(KycPaymentOffice::getName)
                .orElse("");
    }

}
