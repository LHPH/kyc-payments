package com.kyc.payments.repositories;

import com.kyc.payments.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionsEntity,Long> {
}
