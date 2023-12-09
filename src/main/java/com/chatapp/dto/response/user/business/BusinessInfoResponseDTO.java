package com.chatapp.dto.response.user.business;


import com.chatapp.dto.response.user.UserInfoResponseDTO;

public class BusinessInfoResponseDTO extends UserInfoResponseDTO{
    String representor;
    String taxCode;
    String address;
    String activeTime;

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
}
