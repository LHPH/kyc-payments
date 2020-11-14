package com.kyc.payments.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public User retrieveCustomerByUser(String user){

        User userDatabase = new User("admin",
                "$2a$10$KJsjXh68CUFNTLDpwEQzheL.RQIlF04QuWqonECSnTUyQmrNY8wn.", new ArrayList<>());
        return userDatabase;
    }

}
