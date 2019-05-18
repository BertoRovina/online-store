package com.hrovina.onlinestore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Client email is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
