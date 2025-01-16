package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "KYC_PAYMENT_OFFICE")
public class KycPaymentOffice extends BaseCatalog{

    @Column(name = "STATUS")
    private Boolean status;

}
