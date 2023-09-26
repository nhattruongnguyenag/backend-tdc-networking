package com.chatapp.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
