package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Setter
@Getter
public class ServiceChargeEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    private ServiceEntity service;

    @ManyToOne
    private CustomerEntity customer;

    @Column(name = "REFERENCE_PAYMENT")
    private String reference;

    @Column(name = "DATE_CHARGE")
    private Date date;

    @OneToOne
    private PaymentEntity payment;

}
