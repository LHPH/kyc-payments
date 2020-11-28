package com.kyc.payments.repositories;

import com.kyc.payments.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankEntity,Long> {

    BankEntity findByCveBank(String cve);

}
