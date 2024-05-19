package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycCustomerPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KycCustomerPaymentMethodRepository extends JpaRepository<KycCustomerPaymentMethod,Long> {

    @Query(name = "KycCustomerPaymentMethod.findAllCustomerPaymentMethod")
    List<KycCustomerPaymentMethod> findAllCustomerPaymentMethod(@Param("idCustomer") Long idCustomer);

    Optional<KycCustomerPaymentMethod> findByIdCustomerAndIdPaymentMethod(Long idCustomer, Integer idPaymentMethod);

    @Query(name = "KycCustomerPaymentMethod.findAuthMethodToPay")
    Optional<KycCustomerPaymentMethod> findAuthMethodToPay(@Param("idCustomer") Long idCustomer,
                                                           @Param("method") Integer method);
}
