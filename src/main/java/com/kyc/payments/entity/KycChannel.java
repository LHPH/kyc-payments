package com.kyc.payments.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "KYC_CHANNEL")
public class KycChannel extends BaseCatalog{}
