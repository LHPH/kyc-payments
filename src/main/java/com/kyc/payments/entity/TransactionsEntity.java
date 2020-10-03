package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Setter
@Getter
public class TransactionsEntity {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "DESTINATION")
    private String destination;
    @OneToOne
    private TransactionStatusEntity transactionStatus;
    @OneToOne
    private BankEntity bank;
    @ManyToOne
    private PaymentEntity payment;
    @Column(name = "DATE_START")
    private Timestamp dateStart;
    @Column(name = "DATE_FINISH")
    private Timestamp dateFinish;

}
