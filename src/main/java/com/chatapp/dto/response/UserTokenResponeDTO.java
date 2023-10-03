package com.chatapp.dto.response;

import lombok.Data;

@Data
public class UserTokenResponeDTO {
    private StudentInfoResponeDTO student;
    private FacultyInfoResponeDTO faculty;
    private BusinessInfoResponseDTO business;
}
