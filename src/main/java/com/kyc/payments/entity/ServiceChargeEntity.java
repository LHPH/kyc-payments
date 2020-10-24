package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "SERVICE_CHARGE")
public class ServiceChargeEntity {

    @Id
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @OneToOne(mappedBy = "serviceCharge",cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private ServiceChargeDetailEntity serviceChargeDetail;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "serviceCharge",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<PaymentEntity> payments;

}
