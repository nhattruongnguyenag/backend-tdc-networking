package com.chatapp.dto.response;

import lombok.*;

@Data
public class StudentInfoResponeDTO {
    UserInfoResponseDTO user;
    String facultyName;
    String major;
    String studentCode;
}
