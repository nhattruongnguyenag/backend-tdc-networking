package com.chatapp.enums;

public enum Option {
    LANGUAGE("language");

    private String value;

    Option(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

