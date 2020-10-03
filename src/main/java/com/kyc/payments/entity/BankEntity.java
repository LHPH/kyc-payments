package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Setter
@Getter
public class BankEntity {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="CVE_BANK")
    private String cveBank;

    @Column(name="NAME")
    private String nameBank;

}
