package com.chatapp.enums;

public enum QuestionType {
    SHORT("tra-loi-ngan"),
    ONE("chon-mot-dap-an"),
    MULTIPLE("chon-nhieu-dap-an");

    private String role;

    QuestionType(String name) {
        this.role = name;
    }

    public String getName() {
        return role;
    }
}
