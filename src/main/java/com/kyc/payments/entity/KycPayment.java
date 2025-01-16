package com.kyc.payments.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "KYC_PAYMENT")
public class KycPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLIO")
    private Long folio;

    @Column(name = "CHANNEL")
    private Integer channel;

    @Column(name = "PAYMENT_METHOD")
    private Integer paymentMethod;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "MOTIVE")
    private String motive;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "ID_STATUS")
    private Integer idStatus;

    @Column(name = "ID_BILL")
    private Integer idBill;

    @Column(name = "ID_PAYMENT_OFFICE")
    private Integer idPaymentOffice;

    @Column(name = "ID_USER_TRANSACTOR")
    private Long idUser;

    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "DATE_PAYMENT",columnDefinition = "DATETIME")
    private LocalDateTime datePayment;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "payment",cascade = CascadeType.ALL)
    private List<KycTransaction> transactions;

}
