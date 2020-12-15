package com.kyc.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;

@SpringBootApplication(exclude = {MessageSourceAutoConfiguration.class})
public class KycPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycPaymentsApplication.class, args);
	}

}
