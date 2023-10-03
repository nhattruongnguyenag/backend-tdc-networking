package com.chatapp.enums;

public enum Role {
    BUSINESS("doanh-nghiep"),
    FACULTY("khoa"),
    STUDENT("sinh-vien");

    private String role;

    Role(String name) {
        this.role = name;
    }

    public String getName() {
        return role;
    }
}