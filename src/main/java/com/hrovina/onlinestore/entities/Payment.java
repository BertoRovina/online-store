package com.hrovina.onlinestore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="order_id")
    @MapsId
    private PurchaseOrder purchaseOrder;

    public Payment() {
    }

    public Payment(Integer id, PaymentState state, PurchaseOrder purchaseOrder) {
        this.id = id;
        this.state =  (state == null) ? null : state.getCod();
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getId() {
        return id;
    }

    public PaymentState getState() {
        return PaymentState.toEnum(state);
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(PaymentState state) {
        this.state = state.getCod();
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}