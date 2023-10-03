package com.chatapp.enums;

public enum Role {
    ADMIN("doanh-nghiep"),
    SHIPPER("khoa"),
    CUSTOMER("sinh-vien");

    private String role;

    Role(String name) {
        this.role = name;
    }

    public String getName() {
        return role;
    }
}