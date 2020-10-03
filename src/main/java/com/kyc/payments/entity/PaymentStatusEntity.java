package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Setter
@Getter
public class PaymentStatusEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;


}
