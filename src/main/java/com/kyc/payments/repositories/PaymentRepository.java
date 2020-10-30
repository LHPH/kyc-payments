package com.kyc.payments.repositories;

import com.kyc.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
