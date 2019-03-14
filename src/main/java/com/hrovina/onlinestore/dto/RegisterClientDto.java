package com.hrovina.onlinestore.dto;

import java.io.Serializable;

public class RegisterClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String doc;
    private Integer clientType;

    private String address;
    private String number;
    private String additionalAddressInfo;
    private String area;
    private String zipCode;

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
