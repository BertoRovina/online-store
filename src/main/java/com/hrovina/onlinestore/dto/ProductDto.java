package com.hrovina.onlinestore.dto;

import com.hrovina.onlinestore.entities.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private double price;

    public ProductDto() {
    }

    public ProductDto(Product obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
