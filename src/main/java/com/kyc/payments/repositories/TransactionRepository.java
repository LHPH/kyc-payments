package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<KycTransaction,Long> {
}
