package com.kyc.payments.repositories;

import com.kyc.payments.entity.ServiceChargeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceChargeDetailRepository extends JpaRepository<ServiceChargeDetailEntity,Long> {

    @Query("from ServiceChargeDetailEntity scd join fetch scd.serviceCharge" +
            " left join fetch scd.serviceCharge.payments where scd.reference=:reference ")
    ServiceChargeDetailEntity findByReference(@Param("reference") String reference);

}
