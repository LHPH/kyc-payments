package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
public class CustomerEntity {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "SECOND_LAST_NAME")
    private String secondLastName;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CELL_PHONE")
    private String cellPhone;
    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToMany
    private List<ServiceChargeEntity> serviceCharges;
}
