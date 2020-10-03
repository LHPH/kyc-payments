package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Setter
@Getter
public class PaymentEntity {

    @Id
    @Column(name = "FOLIO")
    private Integer folio;

    @Column(name = "PAYMENT_SOURCE")
    private String paymentSource;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "MOTIVE")
    private String motive;

    @OneToOne
    private PaymentStatusEntity paymentStatus;

}
