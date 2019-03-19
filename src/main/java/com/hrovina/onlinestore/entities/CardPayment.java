package com.hrovina.onlinestore.entities;


import com.fasterxml.jackson.annotation.JsonTypeName;
import enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;
    private Integer monthlyInstallments;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentState state, PurchaseOrder purchaseOrder, Integer monthlyInstallments) {
        super(id, state, purchaseOrder);
        this.monthlyInstallments = monthlyInstallments;
    }

    public Integer getMonthlyInstallments() {
        return monthlyInstallments;
    }

    public void setMonthlyInstallments(Integer monthlyInstallments) {
        this.monthlyInstallments = monthlyInstallments;
    }
}
