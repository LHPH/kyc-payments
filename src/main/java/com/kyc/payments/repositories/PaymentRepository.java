package com.kyc.payments.repositories;

import com.kyc.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    @Query("from PaymentEntity p join fetch p.transactions t join fetch t.transactionStatus where p.folio=:folio")
    PaymentEntity findByFolio(@Param("folio") Long folio);

}
