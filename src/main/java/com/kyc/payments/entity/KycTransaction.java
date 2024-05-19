package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
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
