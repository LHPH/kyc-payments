package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

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
