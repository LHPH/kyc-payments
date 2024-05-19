package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
