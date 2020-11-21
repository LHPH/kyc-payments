package com.kyc.payments.repositories;

import com.kyc.payments.entity.ServiceChargeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceChargeDetailRepository extends JpaRepository<ServiceChargeDetailEntity,Long> {

    ServiceChargeDetailEntity findByReference(String reference);

}
