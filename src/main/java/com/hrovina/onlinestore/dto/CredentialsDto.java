package com.hrovina.onlinestore.dto;

public class CredentialsDto {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public CredentialsDto() {
    }

    public CredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
