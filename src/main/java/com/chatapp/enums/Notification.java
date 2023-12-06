package com.chatapp.enums;

public enum Notification {
    REGISTER_SUCCESS("resgister_success"),
    CHANGE_PASSWORD_SUCCESS("change_password_success"),
    CREATE_SURVEY("create_survey");

    private String value;

    Notification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
