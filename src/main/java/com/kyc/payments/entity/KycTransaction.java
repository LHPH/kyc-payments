package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "KYC_TRANSACTION")
public class KycTransaction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ID_STATUS")
    private Integer idStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLIO_PAYMENT",referencedColumnName = "FOLIO")
    private KycPayment payment;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DATE_TRANSACTION")
    private Date dateTransaction;
}
