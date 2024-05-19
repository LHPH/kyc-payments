package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
