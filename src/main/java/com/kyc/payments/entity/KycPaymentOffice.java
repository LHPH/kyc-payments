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
@Table(name = "KYC_PAYMENT_OFFICE")
public class KycPaymentOffice extends BaseCatalog{

    @Column(name = "STATUS")
    private Boolean status;

}
