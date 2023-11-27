package com.chatapp.dto.response.user;

import com.chatapp.dto.BaseDTO;

import lombok.Data;

@Data
public class UserDTO extends BaseDTO {
    private String fullName;
    private String email;
    private String password;
    private String image;
    private Byte status;
}
