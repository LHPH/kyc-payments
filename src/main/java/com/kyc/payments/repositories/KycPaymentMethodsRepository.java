package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycPaymentMethodsRepository extends JpaRepository<KycPaymentMethod,Integer> {}
