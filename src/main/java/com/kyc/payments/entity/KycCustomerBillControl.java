package com.kyc.payments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "KYC_CUSTOMER_BILL_CONTROL")
public class KycCustomerBillControl {

    @Id
    private Long id;

    @Column(name = "TAXES")
    private Double taxes;

    @Column(name = "SUBTOTAL_AMOUNT")
    private Double subtotalAmount;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "SETTLED")
    private Boolean settled;

    @Column(name = "ID_STATUS")
    private Integer idStatus;

    @Column(name = "ISSUE_DATE",columnDefinition = "DATETIME")
    private LocalDateTime issueDate;

    @Column(name = "BILLING_START_DATE",columnDefinition = "DATE")
    private LocalDate billingStart;

    @Column(name = "BILLING_FINISH_DATE",columnDefinition = "DATE")
    private LocalDate billingFinish;

    @Column(name = "PAYMENT_DUE_DATE",columnDefinition = "DATE")
    private LocalDate paymentDue;

    @Column(name = "SETTLEMENT_DATE",columnDefinition = "DATETIME")
    private LocalDateTime settlementDate;
}
