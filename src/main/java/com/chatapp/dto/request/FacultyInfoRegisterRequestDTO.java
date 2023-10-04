package com.chatapp.dto.request;

import lombok.Data;

@Data
public class FacultyInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String code;
}
