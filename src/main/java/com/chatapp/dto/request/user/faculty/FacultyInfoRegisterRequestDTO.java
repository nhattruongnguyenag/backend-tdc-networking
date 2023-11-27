package com.chatapp.dto.request.user.faculty;

import lombok.Data;

@Data
public class FacultyInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String phone;
    private String code;
}
