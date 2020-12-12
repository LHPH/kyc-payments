package com.kyc.payments.repositories;

import com.kyc.payments.entity.ServiceChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;

public interface ServiceChargeRepository extends JpaRepository<ServiceChargeEntity,Long> {

}
