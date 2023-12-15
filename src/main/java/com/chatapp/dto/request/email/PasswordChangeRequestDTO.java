package com.chatapp.dto.request.email;

public class PasswordChangeRequestDTO {
    private Long userId;
    private String password;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
