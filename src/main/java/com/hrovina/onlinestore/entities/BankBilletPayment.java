package com.hrovina.onlinestore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("bankBilletPayment")
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

    @JsonFormat(pattern = "MM/dd/yyyy hh:mm")
    public Date getExpireDate() {
        return expireDate;
    }

    @JsonFormat(pattern = "MM/dd/yyyy hh:mm")
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
