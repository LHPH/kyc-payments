package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class BaseCatalog {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="DESCRIPTION")
    private String name;
}
