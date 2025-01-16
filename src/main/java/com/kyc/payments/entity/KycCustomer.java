package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
