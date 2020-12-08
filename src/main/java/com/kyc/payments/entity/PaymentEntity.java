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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "PAYMENT")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLIO")
    private Long folio;

    @Column(name = "PAYMENT_SOURCE")
    private String paymentSource;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "MOTIVE")
    private String motive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICE_CHARGE",referencedColumnName = "ID")
    private ServiceChargeEntity serviceCharge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATUS_PAYMENT",referencedColumnName = "ID")
    private PaymentStatusEntity paymentStatus;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "payment",cascade = CascadeType.ALL)
    private List<TransactionsEntity> transactions;

}
