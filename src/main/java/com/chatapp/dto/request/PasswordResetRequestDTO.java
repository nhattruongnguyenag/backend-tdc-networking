package com.chatapp.dto.request;

import lombok.Data;

@Data
public class PasswordResetRequestDTO {
    private String token;
    private String password;
    private String repassword;
}
