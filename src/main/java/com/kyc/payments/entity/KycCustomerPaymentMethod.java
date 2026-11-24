package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;

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
    @ColumnTransformer(read = "SP_KYC_DECRYPT(account)", write = "SP_KYC_ENCRYPT(?)")
    private String account;

    @Column(name = "AUTHORIZE")
    private Boolean authorize;

    @Column(name = "DIRECT_DEBIT")
    private Boolean directDebit;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;
}
