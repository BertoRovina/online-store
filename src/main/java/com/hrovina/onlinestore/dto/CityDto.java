package com.hrovina.onlinestore.dto;

import com.hrovina.onlinestore.entities.City;
import com.hrovina.onlinestore.entities.State;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CityDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "State name is mandatory")
    @Length(min = 5, max = 80, message = "Must be between 5 and 80 characters")
    private String name;

    public CityDto() {
    }

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
