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
@Table(name = "AUTHORIZED_BANKS")
public class BankEntity {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="CVE_BANK")
    private String cveBank;

    @Column(name="NAME")
    private String nameBank;

}
