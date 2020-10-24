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
@Table(name = "SERVICES")
public class ServiceEntity {

    @Id
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name = "COST")
    private String cost;


}
