package com.hrovina.onlinestore.dto;

import com.hrovina.onlinestore.services.exceptions.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class RegisterClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Client name is mandatory")
    @Length(min = 5, max = 120, message = "Must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Client email is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty(message = "Client doc is mandatory")
    @Length(min = 9, max = 9, message = "Invalid doc, should have 9 digits")
    private String doc;

    private Integer clientType;

    @NotEmpty(message = "Client address is mandatory")
    private String address;

    @NotEmpty(message = "Client address number is mandatory")
    private String number;
    private String additionalAddressInfo;
    private String area;

    @NotEmpty(message = "Client zip code is mandatory")
    private String zipCode;

    @NotEmpty(message = "At least one phone number must be given")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public RegisterClientDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAdditionalAddressInfo(String additionalAddressInfo) {
        this.additionalAddressInfo = additionalAddressInfo;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDoc() {
        return doc;
    }

    public Integer getClientType() {
        return clientType;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getAdditionalAddressInfo() {
        return additionalAddressInfo;
    }

    public String getArea() {
        return area;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public Integer getCityId() {
        return cityId;
    }
}
