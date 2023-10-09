package com.chatapp.enums;

public enum PostType {
    NORMAL("thong-thuong"),
    RECRUIMENT("tuyen-dung"),
    SURVEY("khao-sat"),
    LIVESTREAM("phat-song");

    private String role;

    PostType(String name) {
        this.role = name;
    }

    public String getName() {
        return role;
    }
}