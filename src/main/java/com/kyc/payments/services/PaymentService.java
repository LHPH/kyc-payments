package com.kyc.payments.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class PaymentService {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
}
