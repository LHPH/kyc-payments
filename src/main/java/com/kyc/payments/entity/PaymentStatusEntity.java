package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "PAYMENT_STATUS")
public class PaymentStatusEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;


}
