package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycPayment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KycPaymentRepository extends JpaRepository<KycPayment,Long> {

    @Query(name = "KycPayment.findByFolioWithTransactions")
    Optional<KycPayment> findByFolioWithTransactions(@Param("folio") Long folio);

    @Query(name = "KycPayment.findByFolioAndCustomer")
    Optional<KycPayment> findByFolioAndCustomer(@Param("folio") Long folio,
                                     @Param("idCustomer") Long idCustomer);

    Optional<KycPayment> findByReference(String reference);

    @Query(name = "KycPayment.findByReferenceAndCustomer")
    Optional<KycPayment> findByReferenceAndCustomer(@Param("reference") String reference,
                                                    @Param("idCustomer") Long idCustomer);

    @Query(name = "KycPayment.findPaymentsFromDate")
    List<KycPayment> findPaymentsFromDate(@Param("dateInitial") LocalDateTime dateInitial,
                                                    @Param("dateFinish") LocalDateTime dateFinish,
                                                    Pageable pageable);

    @Query(name = "KycPayment.findPaymentsFromDateAndCustomer")
    List<KycPayment> findPaymentsFromDateAndCustomer(@Param("dateInitial") LocalDateTime dateInitial,
                                                    @Param("dateFinish") LocalDateTime dateFinish,
                                                    @Param("idCustomer") Long idCustomer,
                                                    Pageable pageable);
}
