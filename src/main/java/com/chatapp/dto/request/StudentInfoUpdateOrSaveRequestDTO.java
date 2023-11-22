package com.chatapp.dto.request;

import lombok.Data;

@Data
public class StudentInfoUpdateOrSaveRequestDTO {
    private Long id;
    private String email;
    private String name;
    private String image;
    private String phone;
    private String code;
    private Long facultyId;
    private Long majorId;
    private String studentCode;
}
