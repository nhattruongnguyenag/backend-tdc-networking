package com.chatapp.dto.request.user.student;

import lombok.Data;

@Data
public class StudentInfoRegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private String image;
    private String background;
    private String phone;
    private String code;
    private Long facultyId;
    private Long majorId;
    private String studentCode;
    private String subject;
    private String content;
}
