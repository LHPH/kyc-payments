package com.kyc.payments.repositories;

import com.kyc.payments.entity.KycUser;
import com.kyc.payments.services.KycAuthService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KycUserRepository extends JpaRepository<KycUser,Long> {

    @Query("from KycUser user where user.username=:username")
    KycUser getUserByUsername(@Param("username") String username);

}
