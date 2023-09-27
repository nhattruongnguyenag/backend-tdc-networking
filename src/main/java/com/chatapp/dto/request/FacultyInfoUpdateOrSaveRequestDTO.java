package com.chatapp.dto.request;

import lombok.Data;

@Data
public class FacultyInfoUpdateOrSaveRequestDTO {
    private Long id;
    private String email;
    private String fullname;
    private String facultyName;
}