package com.hrovina.onlinestore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "MM/dd/yyyy hh:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "id.purchaseOrder")
    private Set<OrderItem> itemSet = new HashSet<>();

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, Date instant, Client client, Address address) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.address = address;
    }

    public double getTotalAmount(){
        double total = 0;
        for (OrderItem item : itemSet){
            total += item.getSubTotal();
        }
        return total;
    }

    public Set<OrderItem> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<OrderItem> itemSet) {
        this.itemSet = itemSet;
    }

    public Integer getId() {
        return id;
    }

    public Date getInstant() {
        return instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public Client getClient() {
        return client;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder purchaseOrder = (PurchaseOrder) o;
        return id.equals(purchaseOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}