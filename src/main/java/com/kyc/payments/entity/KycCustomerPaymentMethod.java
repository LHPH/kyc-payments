package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="KYC_CUSTOMER_PAYMENT_METHOD")
@Setter
@Getter
public class KycCustomerPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "ID_PAYMENT_METHOD")
    private Integer idPaymentMethod;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "AUTHORIZE")
    private Boolean authorize;

    @Column(name = "DIRECT_DEBIT")
    private Boolean directDebit;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;
}
