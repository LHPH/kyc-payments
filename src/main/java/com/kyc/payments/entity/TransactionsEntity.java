package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionsEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DATE_START")
    private Timestamp dateStart;

    @Column(name = "DATE_FINISH")
    private Timestamp dateFinish;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_BANK",referencedColumnName = "ID")
    private BankEntity bank;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_FOLIO_PAYMENT",referencedColumnName = "FOLIO")
    private PaymentEntity payment;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_STATUS",referencedColumnName = "ID")
    private TransactionStatusEntity transactionStatus;

}
