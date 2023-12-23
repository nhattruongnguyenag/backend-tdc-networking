package com.chatapp.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "business_infos")
public class BusinessesInfoEntity extends BaseEntity {

    @Column(name = "representor", nullable = false )
    private String representor;

    @Column(name = "tax_code", nullable = false)
    private String taxCode;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "active_time", nullable = false)
    private String activeTime;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getRepresentor() {
        return representor;
    }

    public void setRepresentor(String representor) {
        this.representor = representor;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
