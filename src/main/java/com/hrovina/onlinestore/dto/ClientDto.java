package com.hrovina.onlinestore.dto;

import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.services.exceptions.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Client name is mandatory")
    @Length(min = 5, max = 120, message = "Must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Client email is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    public ClientDto() {
    }

    public ClientDto(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
