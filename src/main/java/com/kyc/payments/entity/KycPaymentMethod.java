package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "KYC_PAYMENT_METHODS")
public class KycPaymentMethod extends BaseCatalog{}
