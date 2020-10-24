package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "SERVICE_CHARGE")
public class ServiceChargeDetailEntity {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private ServiceChargeEntity serviceCharge;

    @Column(name = "REFERENCE_PAYMENT")
    private String reference;

    @Column(name = "AMOUNT_CHARGE")
    private String amountCharge;

    @Column(name = "PAID")
    private Boolean paid;

    @Column(name = "DATE_CHARGE")
    private Date date;


}
