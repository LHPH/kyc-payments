package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "SERVICE_CHARGE")
public class ServiceChargeEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICE",referencedColumnName = "ID")
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUSTOMER",referencedColumnName = "ID")
    private CustomerEntity customer;

    @OneToOne(mappedBy = "serviceCharge",cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private ServiceChargeDetailEntity serviceChargeDetail;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "serviceCharge",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<PaymentEntity> payments;

}
