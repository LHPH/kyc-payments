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
@Table(name = "KYC_CUSTOMER")
public class KycCustomer {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "ID_USER")
    private Long idUser;
}
