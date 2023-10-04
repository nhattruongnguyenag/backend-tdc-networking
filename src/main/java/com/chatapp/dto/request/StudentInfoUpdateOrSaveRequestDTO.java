package com.chatapp.dto.request;

import lombok.Data;

@Data
public class StudentInfoUpdateOrSaveRequestDTO {
    private Long id;
    private String email;
    private String name;
    private String image;
    private String code;
    private String facultyName;
    private String major;
    private String studentCode;
}
