package com.kyc.payments.repositories;

import com.kyc.payments.entity.ServiceChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceChargeRepository extends JpaRepository<ServiceChargeEntity,Long> {
}
