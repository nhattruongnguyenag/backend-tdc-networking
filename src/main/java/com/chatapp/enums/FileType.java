package com.chatapp.enums;

public enum FileType {
    IMAGE("images/"),
    FILE("files/");

    private String role;

    FileType(String name) {
        this.role = name;
    }

    public String getName() {
        return role;
    }
}
