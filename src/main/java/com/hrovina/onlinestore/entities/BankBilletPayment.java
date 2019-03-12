package com.hrovina.onlinestore.entities;

import enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankBilletPayment extends Payment {
    private static final long serialVersionUID = 1L;
    private Date expireDate;
    private Date paymentDate;

    public BankBilletPayment() {
    }

    public BankBilletPayment(Integer id, PaymentState state, PurchaseOrder purchaseOrder, Date expireDate, Date paymentDate) {
        super(id, state, purchaseOrder);
        this.expireDate = expireDate;
        this.paymentDate = paymentDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
