package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KycCustomerRepository extends JpaRepository<KycCustomer,Long> {

    Optional<KycCustomer> findByIdUser(Long idUser);
}
