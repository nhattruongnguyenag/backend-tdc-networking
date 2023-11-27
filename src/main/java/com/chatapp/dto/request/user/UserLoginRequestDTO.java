package com.chatapp.dto.request.user;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
