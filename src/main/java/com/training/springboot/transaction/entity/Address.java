package com.training.springboot.transaction.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ADDRESS")
public class Address {
    //jpa akan bikin table dengan nama sama dengan nama class (address) apabila tidak ada anotasi @Table
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String namaPenerima;
    private String kodePos;
    private Boolean primaryAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public Boolean getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
}
