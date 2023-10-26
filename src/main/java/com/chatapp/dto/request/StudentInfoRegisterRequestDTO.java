package com.chatapp.dto.request;

import lombok.Data;

@Data
public class StudentInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String phone;
    private String code;
    private String facultyName;
    private String major;
    private String studentCode;
}
