package com.kyc.payments.services;

import com.kyc.payments.entity.KycUser;
import com.kyc.payments.repositories.KycUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class KycAuthService {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private KycUserRepository kycUserRepository;

    public KycUser retrieveCustomerByUser(String username){

        LOGGER.info("Buscando al usuario {}",username);
        KycUser userFound = kycUserRepository.getUserByUsername(username);
        LOGGER.info("El usuario encontrado en la BD fue {}",userFound);
        return userFound;
    }

}
