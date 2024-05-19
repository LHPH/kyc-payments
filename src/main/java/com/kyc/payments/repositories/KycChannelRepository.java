package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycChannelRepository extends JpaRepository<KycChannel,Integer> {
}
