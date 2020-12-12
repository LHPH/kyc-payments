package com.kyc.payments.repositories;

import com.kyc.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    @Query(name = "Payment.findByFolio")
    PaymentEntity findByFolio(@Param("folio") Long folio);

    @Query(name = "Payment.findPaymentsFromDateAndCustomer")
    List<PaymentEntity> getPaymentsFromDateAndCustomer(@Param("dateInitial") Date dateInitial,
                                                       @Param("dateFinish") Date dateFinish,
                                                       @Param("customer") Integer customer);
}
